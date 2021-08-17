package com.yd.scala.hello.validate;

import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.*;
import org.apache.dubbo.common.bytecode.ClassGenerator;

import javax.validation.Constraint;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ValidationBeanWrapper {

    private static final Map<String, Class<?>> WRAPPER_CLASS = new ConcurrentHashMap<>(128);

    public static Object beanWrapper(Class<?> clazz, Method method, Object[] args) throws Exception {
        final String beanWrapperClass = generateMethodParameterClassName(clazz, method);
        final Class<?> wrapperClass = WRAPPER_CLASS.computeIfAbsent(beanWrapperClass, name -> {
            try {
                return Class.forName(beanWrapperClass, true, clazz.getClassLoader());
            } catch (ClassNotFoundException e) {
                try {
                    return generateMethodParameterClass(clazz, method, beanWrapperClass);
                } catch (Exception ge) {
                    throw new RuntimeException("创建BeanWrapperClass出错(100)", ge);
                }
            } catch (Exception ex) {
                throw new RuntimeException("创建BeanWrapperClass出错(101)", ex);
            }
        });
        // New Bean and set args
        try {
            Object parameterBean = wrapperClass.newInstance();
            for (int i = 0; i < args.length; i++) {
                Field field = wrapperClass.getField(method.getName() + "Argument" + i);
                field.set(parameterBean, args[i]);
            }
            return parameterBean;
        } catch (Exception e) {
            throw new IllegalAccessException("创建BeanWrapper出错");
        }
    }

    /**
     * try to generate methodParameterClass.
     *
     * @param clazz              interface class
     * @param method             invoke method
     * @param parameterClassName generated parameterClassName
     * @return Class<?> generated methodParameterClass
     * @throws Exception
     */
    private static Class<?> generateMethodParameterClass(Class<?> clazz, Method method, String parameterClassName) throws Exception {
        ClassPool pool = ClassGenerator.getClassPool(clazz.getClassLoader());
        synchronized (parameterClassName.intern()) {
            CtClass ctClass = null;
            try {
                ctClass = pool.getCtClass(parameterClassName);
            } catch (NotFoundException ignore) {
            }

            if (null == ctClass) {
                ctClass = pool.makeClass(parameterClassName);
                ClassFile classFile = ctClass.getClassFile();
                classFile.setVersionToJava5();
                ctClass.addConstructor(CtNewConstructor.defaultConstructor(pool.getCtClass(parameterClassName)));
                // parameter fields
                Class<?>[] parameterTypes = method.getParameterTypes();
                Annotation[][] parameterAnnotations = method.getParameterAnnotations();
                for (int i = 0; i < parameterTypes.length; i++) {
                    Class<?> type = parameterTypes[i];
                    Annotation[] annotations = parameterAnnotations[i];
                    AnnotationsAttribute attribute = new AnnotationsAttribute(classFile.getConstPool(), AnnotationsAttribute.visibleTag);
                    for (Annotation annotation : annotations) {
                        if (annotation.annotationType().isAnnotationPresent(Constraint.class)) {
                            javassist.bytecode.annotation.Annotation ja = new javassist.bytecode.annotation.Annotation(
                                    classFile.getConstPool(), pool.getCtClass(annotation.annotationType().getName()));
                            Method[] members = annotation.annotationType().getMethods();
                            for (Method member : members) {
                                if (Modifier.isPublic(member.getModifiers())
                                        && member.getParameterTypes().length == 0
                                        && member.getDeclaringClass() == annotation.annotationType()) {
                                    Object value = member.invoke(annotation);
                                    if (null != value) {
                                        MemberValue memberValue = createMemberValue(
                                                classFile.getConstPool(), pool.get(member.getReturnType().getName()), value);
                                        ja.addMemberValue(member.getName(), memberValue);
                                    }
                                }
                            }
                            attribute.addAnnotation(ja);
                        }
                    }
                    String fieldName = method.getName() + "Argument" + i;
                    CtField ctField = CtField.make("public " + type.getCanonicalName() + " " + fieldName + ";", pool.getCtClass(parameterClassName));
                    ctField.getFieldInfo().addAttribute(attribute);
                    ctClass.addField(ctField);
                }
                return ctClass.toClass(clazz.getClassLoader(), null);
            } else {
                return Class.forName(parameterClassName, true, clazz.getClassLoader());
            }
        }
    }

    private static String generateMethodParameterClassName(Class<?> clazz, Method method) {
        StringBuilder builder = new StringBuilder().append(clazz.getName())
                .append("_")
                .append(toUpperMethodName(method.getName()))
                .append("Parameter");

        Class<?>[] parameterTypes = method.getParameterTypes();
        for (Class<?> parameterType : parameterTypes) {
            builder.append("_").append(parameterType.getName());
        }

        return builder.toString();
    }


    private static String toUpperMethodName(String methodName) {
        return methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
    }

    // Copy from javassist.bytecode.annotation.Annotation.createMemberValue(ConstPool, CtClass);
    private static MemberValue createMemberValue(ConstPool cp, CtClass type, Object value) throws NotFoundException {
        MemberValue memberValue = javassist.bytecode.annotation.Annotation.createMemberValue(cp, type);
        if (memberValue instanceof BooleanMemberValue) {
            ((BooleanMemberValue) memberValue).setValue((Boolean) value);
        } else if (memberValue instanceof ByteMemberValue) {
            ((ByteMemberValue) memberValue).setValue((Byte) value);
        } else if (memberValue instanceof CharMemberValue) {
            ((CharMemberValue) memberValue).setValue((Character) value);
        } else if (memberValue instanceof ShortMemberValue) {
            ((ShortMemberValue) memberValue).setValue((Short) value);
        } else if (memberValue instanceof IntegerMemberValue) {
            ((IntegerMemberValue) memberValue).setValue((Integer) value);
        } else if (memberValue instanceof LongMemberValue) {
            ((LongMemberValue) memberValue).setValue((Long) value);
        } else if (memberValue instanceof FloatMemberValue) {
            ((FloatMemberValue) memberValue).setValue((Float) value);
        } else if (memberValue instanceof DoubleMemberValue) {
            ((DoubleMemberValue) memberValue).setValue((Double) value);
        } else if (memberValue instanceof ClassMemberValue) {
            ((ClassMemberValue) memberValue).setValue(((Class<?>) value).getName());
        } else if (memberValue instanceof StringMemberValue) {
            ((StringMemberValue) memberValue).setValue((String) value);
        } else if (memberValue instanceof EnumMemberValue) {
            ((EnumMemberValue) memberValue).setValue(((Enum<?>) value).name());
        }
        /* else if (memberValue instanceof AnnotationMemberValue) */
        else if (memberValue instanceof ArrayMemberValue) {
            CtClass arrayType = type.getComponentType();
            int len = Array.getLength(value);
            MemberValue[] members = new MemberValue[len];
            for (int i = 0; i < len; i++) {
                members[i] = createMemberValue(cp, arrayType, Array.get(value, i));
            }
            ((ArrayMemberValue) memberValue).setValue(members);
        }
        return memberValue;
    }

}
