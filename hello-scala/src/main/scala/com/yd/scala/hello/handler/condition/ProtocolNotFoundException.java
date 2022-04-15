package com.yd.scala.hello.handler.condition;

public class ProtocolNotFoundException extends RuntimeException {
    public ProtocolNotFoundException(String protocol) {
        super("Protocol " + protocol + "不存在");
    }
}