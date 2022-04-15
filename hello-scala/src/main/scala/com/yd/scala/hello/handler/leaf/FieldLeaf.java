package com.yd.scala.hello.handler.leaf;


import com.yd.scala.hello.handler.protocol.Protocol;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FieldLeaf implements Leaf {

    private final Map<String, Box> protocolMap = new HashMap<>();

    public List<String> getFields() {
        return new LinkedList<>(protocolMap.keySet());
    }

    public void putField(String name, Protocol protocol) {
        protocolMap.put(name, new Box(protocol));
    }

    public Protocol getProtocol(String name) {
        Box box = protocolMap.get(name);
        return box == null ? null : box.getProtocol();
    }

    @Override
    public boolean isElement() {
        return false;
    }

    @Override
    public ElementLeaf toElementLeaf() {
        throw new RuntimeException("FieldLeaf不能转化为ElementLeaf");
    }

    @Override
    public FieldLeaf toFieldLeaf() {
        return this;
    }

    private class Box {
        private final Protocol protocol;

        Box(Protocol protocol) {
            this.protocol = protocol;
        }

        public Protocol getProtocol() {
            return protocol;
        }
    }
}
