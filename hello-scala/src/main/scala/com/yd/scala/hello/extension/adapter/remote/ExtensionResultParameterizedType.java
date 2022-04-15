package com.yd.scala.hello.extension.adapter.remote;

import com.yd.scala.hello.extension.config.ExtensionResult;
import com.yd.scala.hello.extension.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class ExtensionResultParameterizedType implements ParameterizedType {
    private final Type[] arguments;

    public ExtensionResultParameterizedType(Type[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return arguments;
    }

    @Override
    public Type getRawType() {
        return ExtensionResult.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    @Override
    public String toString() {
        return ClassUtils.getTypeLongName(this);
    }
}
