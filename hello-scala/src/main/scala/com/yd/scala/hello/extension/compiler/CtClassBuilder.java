/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yd.scala.hello.extension.compiler;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created By Zeb灬D On 2020-03-28
 * 参考dubbo源码
 *
 */
public class CtClassBuilder {

    private String className;

    private String superClassName = "java.lang.Object";

    private List<String> imports = new ArrayList<>();

    private Map<String, String> fullNames = new HashMap<>();

    private List<String> ifaces = new ArrayList<>();

    private List<Source> fields = new ArrayList<>();

    private List<Source> methods = new ArrayList<>();

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = getQualifiedClassName(superClassName);
    }

    public void addImports(String pkg) {
        int pi = pkg.lastIndexOf('.');
        if (pi > 0) {
            String pkgName = pkg.substring(0, pi);
            this.imports.add(pkgName);
            if (!pkg.endsWith(".*")) {
                fullNames.put(pkg.substring(pi + 1), pkg);
            }
        }
    }

    public void addInterface(String iface) {
        this.ifaces.add(getQualifiedClassName(iface));
    }

    public void addField(String field,List<String> annotations) {
        this.fields.add(new Source(field, annotations));
    }

    public void addMethod(String method,List<String> annotations) {
        this.methods.add(new Source(method, annotations));
    }

    /**
     * get full qualified class name
     *
     * @param className super class name, maybe qualified or not
     */
    protected String getQualifiedClassName(String className) {
        if (className.contains(".")) {
            return className;
        }
        if (fullNames.containsKey(className)) {
            return fullNames.get(className);
        }
        return forName(className).getName();
    }

    /**
     * build CtClass object
     */
    public CtClass build(ClassLoader classLoader) throws NotFoundException, CannotCompileException {
        ClassPool pool = new ClassPool(true);
        pool.appendClassPath(new LoaderClassPath(classLoader));

        // create class
        CtClass ctClass = pool.makeClass(className, pool.get(superClassName));

        // add imported packages
        imports.forEach(pool::importPackage);

        // add implemented interfaces
        for (String iface : ifaces) {
            ctClass.addInterface(pool.get(iface));
        }

        ClassFile ccFile = ctClass.getClassFile();
        ConstPool constpool = ccFile.getConstPool();
        // add fields
        for (Source field : fields) {
            AnnotationsAttribute attribute = getAnnotations(field.annotations,constpool);
            CtField ctField = CtField.make(field.code, ctClass);
            if(attribute != null){
                ctField.getFieldInfo().addAttribute(attribute);
            }
            ctClass.addField(ctField);
        }

        // add methods
        String shortClassName = className;
        int i = className.lastIndexOf(".");
        if(i != -1){
            shortClassName = className.substring(i+1);
        }
        String constructorRegex = "^\\s*(private|public|protected)?\\s+" + shortClassName + "\\s*\\(";
        for (Source method : methods) {
            AnnotationsAttribute attribute = getAnnotations(method.annotations,constpool);
            if(Pattern.compile(constructorRegex).matcher(method.code).find()){
                CtConstructor ctConstructor = CtNewConstructor.make(method.code, ctClass);
                if(attribute != null){
                    ctConstructor.getMethodInfo().addAttribute(attribute);
                }
                ctClass.addConstructor(ctConstructor);
            }else {
                CtMethod ctMethod = CtNewMethod.make(method.code, ctClass);
                if(attribute != null){
                    ctMethod.getMethodInfo().addAttribute(attribute);
                }
                ctClass.addMethod(ctMethod);
            }
        }

        return ctClass;
    }

    private AnnotationsAttribute getAnnotations(List<String> annotations, ConstPool constpool){
        if(annotations == null || annotations.isEmpty()){
            return null;
        }
        AnnotationsAttribute attribute = new AnnotationsAttribute(constpool, AnnotationsAttribute.visibleTag);
        for(String annotation : annotations){
            annotation = annotation.trim().substring(1);
            Class<?> annotationClass = forName(annotation);
            Annotation ann = new Annotation(annotationClass.getName(),constpool);
            attribute.addAnnotation(ann);
        }
        return attribute;
    }

    private Class<?> forName(String className) {
        try {
            return classForName(className);
        } catch (ClassNotFoundException e) {
            if (imports != null && !imports.isEmpty()) {
                for (String pkg : imports) {
                    try {
                        return classForName(pkg + "." + className);
                    } catch (ClassNotFoundException ignored) {

                    }
                }
            }
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private Class<?> classForName(String className) throws ClassNotFoundException {
        if ("boolean".equals(className))
            return boolean.class;
        if ("byte".equals(className))
            return byte.class;
        if ("char".equals(className))
            return char.class;
        if ("short".equals(className))
            return short.class;
        if ("int".equals(className))
            return int.class;
        if ("long".equals(className))
            return long.class;
        if ("float".equals(className))
            return float.class;
        if ("double".equals(className))
            return double.class;
        if ("boolean[]".equals(className))
            return boolean[].class;
        if ("byte[]".equals(className))
            return byte[].class;
        if ("char[]".equals(className))
            return char[].class;
        if ("short[]".equals(className))
            return short[].class;
        if ("int[]".equals(className))
            return int[].class;
        if ("long[]".equals(className))
            return long[].class;
        if ("float[]".equals(className))
            return float[].class;
        if ("double[]".equals(className))
            return double[].class;
        try {
            return arrayForName(className);
        } catch (ClassNotFoundException e) {
            if (className.indexOf('.') == -1) {
                try {
                    return arrayForName("java.lang." + className);
                } catch (ClassNotFoundException ignored) {

                }
            }
            throw e;
        }
    }

    private Class<?> arrayForName(String className) throws ClassNotFoundException {
        return Class.forName(className.endsWith("[]")
                ? "[L" + className.substring(0, className.length() - 2) + ";"
                : className, true, Thread.currentThread().getContextClassLoader());
    }

    static class Source{
        String code;
        List<String> annotations;

        Source(String code,List<String> annotations){
            this.code = code;
            this.annotations = new ArrayList<>(annotations);
        }
    }
}
