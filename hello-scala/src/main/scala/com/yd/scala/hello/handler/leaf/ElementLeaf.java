package com.yd.scala.hello.handler.leaf;


import com.yd.scala.hello.handler.protocol.Protocol;

public class ElementLeaf implements Leaf {
    private Protocol protocol;

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public boolean isElement() {
        return true;
    }

    @Override
    public ElementLeaf toElementLeaf() {
        return this;
    }

    @Override
    public FieldLeaf toFieldLeaf() {
        throw new RuntimeException("ElementLeaf不能转化为FieldLeaf");
    }
}
