package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.condition.UnsupportedMethodException;

import java.util.List;

public abstract class AbstractElementNode extends AbstractNode {
    public AbstractElementNode() {
    }

    public List<String> getFields() {
        throw new UnsupportedMethodException("getFields");
    }

    public String getStringFieldValue(String fieldName) {
        throw new UnsupportedMethodException("getStringFieldValue");
    }

    public void setStringFieldValue(String fieldName, String fieldValue) {
        throw new UnsupportedMethodException("setStringFieldValue");
    }
}