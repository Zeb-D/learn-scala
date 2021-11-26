package com.yd.scala.hello.crypto.handler;

public class EncryptedStringTypeHandler extends EncryptedTypeHandler<String> {
    protected String getOriginalValue(String decryptedValue) {
        return decryptedValue;
    }
}