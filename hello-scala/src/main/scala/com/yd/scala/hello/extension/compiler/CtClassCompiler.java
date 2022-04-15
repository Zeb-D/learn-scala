package com.yd.scala.hello.extension.compiler;


import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created By Zeb灬D On 2020-03-28
 * 源码必须遵守每个语句换行的规则，否则会解析出错
 * 类函数、类变量必须以private|public|protected开头
 **/

public class CtClassCompiler {
    private static final Pattern IMPORT_PATTERN = Pattern.compile("import\\s+([\\w.*]+);\n");

    private static final String CLASS_REGEX = "(private|public|protected)?\\s+class\\s+([\\w.]+)[^{]*\\{\n";

    private static final String FIELD_REGEX = "^(private|public|protected)\\s+(static\\s+)?[^{}]+;$";

    private static final String METHOD_REGEX = "^(private|public|protected)\\s+(static\\s+)?[\\w.]+\\s+\\w+\\s*[(][^()]*[)]\\s*\\{$";

    private static final String ALL_METHOD_REGEX = "^(private|public|protected)(\\s+static)?\\s+[\\w.]+(<\\w+>)?(\\s+\\w+)?\\s*[(][^()]*[)]\\s*\\{$";

    private static final String ANNOTATION_REGEX = "^\\s*@[\\w.]+\\s*$";


    public static Class compile(String source,ClassLoader classLoader) throws Throwable {

        source = source.replaceAll("(/\\*{1,2}[\\s\\S]*?\\*/)|(//[\\s\\S]*?\\n)","");
        source = source.replaceAll("\\n\\s*\\n","\n");

        Matcher matcher = Pattern.compile(CLASS_REGEX).matcher(source);
        if (!matcher.find()) {
            throw new IllegalStateException("class source 不合法");
        }
        String classLineSource = matcher.group(0).trim();
        String className = subKeyString(classLineSource,"class","[\\s{]");
        if(isBlank(className)){
            throw new IllegalStateException("class source 不合法");
        }

        CtClassBuilder builder = new CtClassBuilder();
        builder.setClassName(className);

        String[] classSources = source.split(CLASS_REGEX);
        String classHeader = classSources[0];
        String classBody = classSources[1];

        ClassPool pool = new ClassPool(true);
        pool.appendClassPath(new LoaderClassPath(classLoader));
        matcher = IMPORT_PATTERN.matcher(classHeader);
        while (matcher.find()) {
            builder.addImports(matcher.group(1).trim());
        }

        String extendsLine = subKeyString(classLineSource,"extends","[\\s{]");
        if(!isBlank(extendsLine)){
            builder.setSuperClassName(extendsLine);
        }

        String implementsLine = subKeyString(classLineSource,"implements","\\{");
        if(!isBlank(implementsLine)){
            String[] ifaces = implementsLine.trim().split(",");
            Arrays.stream(ifaces).forEach(i -> builder.addInterface(i.trim()));
        }

        classBody = classBody.substring(0,classBody.lastIndexOf("}"));
        String[] lines = classBody.split("\n");
        List<String> annotations = new ArrayList<>(64);

        FieldOrMethod fieldOrMethod = null;
        for(String line : lines){
            line = line.trim();
            if(line.matches(ANNOTATION_REGEX)){
                addFieldOrMethod(builder,fieldOrMethod);
                fieldOrMethod = null;
                annotations.add(line);
                continue;
            }

            if(line.matches(FIELD_REGEX)){
                addFieldOrMethod(builder,fieldOrMethod);
                fieldOrMethod = new FieldOrMethod(true,annotations);
                annotations.clear();
            } else if(line.matches(ALL_METHOD_REGEX)){
                addFieldOrMethod(builder,fieldOrMethod);
                fieldOrMethod = new FieldOrMethod(false,annotations);
                annotations.clear();
            }
            if(fieldOrMethod != null){
                fieldOrMethod.appendLine(line);
            }
        }

        if(fieldOrMethod != null){
            addFieldOrMethod(builder,fieldOrMethod);
        }

        CtClass cls = builder.build(classLoader);
        return cls.toClass(classLoader, CtClassCompiler.class.getProtectionDomain());
    }

    private static void addFieldOrMethod(CtClassBuilder builder,FieldOrMethod fieldOrMethod){
        if(fieldOrMethod == null){
            return;
        }
        if(fieldOrMethod.isField){
            builder.addField(fieldOrMethod.code.toString(),fieldOrMethod.annotations);
        }else {
            builder.addMethod(fieldOrMethod.code.toString(),fieldOrMethod.annotations);
        }
    }

    private static boolean isBlank(String str){
        return str == null || str.trim().length() == 0;
    }

    private static String subKeyString(String src,String key,String regex){
        int index = src.indexOf(key);
        if(index == -1){
            return null;
        }
        String subStr = src.substring(index + key.length()).trim();
        return subStr.split(regex)[0];
    }

    private static class FieldOrMethod{
        final boolean isField;
        final List<String> annotations;
        final StringBuffer code = new StringBuffer();

        FieldOrMethod(boolean isField,List<String> annotations){
            this.isField = isField;
            this.annotations = new ArrayList<>(annotations);
        }

        void appendLine(String str){
            code.append(str).append("\n");
        }
    }

}
