package com.yd.scala.hello.extension.config;

import java.io.Serializable;

/**
 * @description 安防扩展插件入参
 * @author Zeb灬D
 * @date 2021/5/7 10:22 上午
 */

public class ExtensionParameter implements Serializable {

    private static final long serialVersionUID = 1219056282990346534L;
    /**
     * 参数类型列表
     */
    private String[] parameterTypes;
    /**
     * 参数值
     */
    private Object[] parameters;
    /**
     * 返回类型
     */
    private String returnType;
    /**
     * 前一链路处理结果，可能为空
     */
    private Object returnObject;

    public String[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(String[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }
}
