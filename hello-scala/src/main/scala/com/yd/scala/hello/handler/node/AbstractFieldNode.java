package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.condition.UnsupportedMethodException;
import com.yd.scala.hello.handler.leaf.FieldLeaf;
import com.yd.scala.hello.handler.leaf.Leaf;

import java.util.Collections;
import java.util.List;

public abstract class AbstractFieldNode<T> extends AbstractNode {
    protected T data;

    public AbstractFieldNode() {
    }

    public List<String> getFields() {
        Leaf leaf = this.path.getLeaf();
        if (leaf != null && !leaf.isElement()) {
            if (this.data == null) {
                return Collections.emptyList();
            } else {
                FieldLeaf fieldLeaf = leaf.toFieldLeaf();
                return fieldLeaf.getFields();
            }
        } else {
            return Collections.emptyList();
        }
    }

    public int getElementSize() {
        throw new UnsupportedMethodException("getElementSize");
    }

    public String getStringElementValue(int index) {
        throw new UnsupportedMethodException("getStringElementValue");
    }

    public void setStringElementValue(int index, String elementValue) {
        throw new UnsupportedMethodException("setStringElementValue");
    }
}