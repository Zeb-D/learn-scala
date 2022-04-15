package com.yd.scala.hello.handler.condition;

public class UnsupportedMethodException extends RuntimeException {
    public UnsupportedMethodException(String method) {
        super("不支持的" + method + "方法");
    }
}