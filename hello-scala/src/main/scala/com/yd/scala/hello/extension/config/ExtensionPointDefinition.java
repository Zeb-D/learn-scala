package com.yd.scala.hello.extension.config;

import java.lang.reflect.Type;

/**
 * @author Zeb灬D
 * @date 2021/5/22 9:30 上午
 */

public class ExtensionPointDefinition {
    private String extensionPointName;
    private String beanName;
    private String className;
    private Type returnType;
    private String methodName;
    private Class<?>[] parameterTypes;

    public String getExtensionPointName() {
        return extensionPointName;
    }

    public void setExtensionPointName(String extensionPointName) {
        this.extensionPointName = extensionPointName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Type getReturnType() {
        return returnType;
    }

    public void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
