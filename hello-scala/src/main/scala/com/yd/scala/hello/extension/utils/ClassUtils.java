package com.yd.scala.hello.extension.utils;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class ClassUtils {

    public static ClassLoader getClassLoader() {
        return getClassLoader(ClassUtils.class);
    }

    public static ClassLoader getClassLoader(Class<?> clazz) {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignored) {

        }
        if (cl == null) {
            cl = clazz.getClassLoader();
        }
        if (cl == null) {
            try {
                cl = ClassLoader.getSystemClassLoader();
            } catch (Throwable ignored) {

            }
        }
        return cl;
    }

    public static Class<?> fromName(String className) throws ClassNotFoundException {
        return forName(className, getClassLoader());
    }

    public static Class<?> forName(String className, ClassLoader classLoader) throws ClassNotFoundException {
        if (className.endsWith("[]")) {
            className = className.substring(0, className.length() - 2);
            Class<?> clazz = forName(className, classLoader);
            return Array.newInstance(clazz, 0).getClass();
        }
        int internalArrayMarker = className.indexOf("[L");
        if (internalArrayMarker != -1 && className.endsWith(";")) {
            if (internalArrayMarker == 0) {
                className = className.substring(2, className.length() - 1);
            } else if (className.startsWith("[")) {
                className = className.substring(1);
            }
            Class<?> clazz = forName(className, classLoader);
            return Array.newInstance(clazz, 0).getClass();
        }
        return classLoader.loadClass(className);
    }

    public static String getTypeLongName(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] argumentTypes = parameterizedType.getActualTypeArguments();
            String[] typeNames = new String[argumentTypes.length];
            for (int i = 0; i < argumentTypes.length; ++i) {
                typeNames[i] = getTypeLongName(argumentTypes[i]);
            }
            return parameterizedType.getRawType().getTypeName() + "<" + String.join(",", typeNames) + ">";
        } else {
            return type.getTypeName();
        }
    }

    public static boolean typeEquals(Type t1, Type t2) {
        if (t1 instanceof ParameterizedType) {
            if (!(t2 instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType pt1 = (ParameterizedType) t1;
            ParameterizedType pt2 = (ParameterizedType) t2;
            if (pt1.getRawType() != pt2.getRawType()) {
                return false;
            }
            Type[] ats1 = pt1.getActualTypeArguments();
            Type[] ats2 = pt2.getActualTypeArguments();
            if (ats1.length != ats2.length) {
                return false;
            }
            for (int i = 0; i < ats1.length; ++i) {
                if (!typeEquals(ats1[i], ats2[i])) {
                    return false;
                }
            }
            return true;
        } else {
            return t1 == t2;
        }
    }
}
