package com.yd.scala.hello.handler.leaf;

public interface Leaf {
    boolean isElement();

    ElementLeaf toElementLeaf();

    FieldLeaf toFieldLeaf();
}
