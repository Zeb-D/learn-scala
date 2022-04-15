package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.path.ArrayChildPath;
import com.yd.scala.hello.handler.path.ChildPath;
import com.yd.scala.hello.handler.path.Path;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArrayNode extends AbstractElementNode {
    private final Object[] data;

    public static ArrayNode create(Path path, Object[] data) {
        return new ArrayNode(path, data);
    }

    private ArrayNode(Path path, Object[] data) {
        this.data = data;
        this.path = path;
    }

    public List<Node> getChildren() {
        ChildPath childPath = this.path.getChild();
        if (childPath != null && childPath.isArray()) {
            if (this.data != null && this.data.length != 0) {
                ArrayChildPath arrayChildPath = childPath.toArrayChildPath();
                List<Node> nodes = new LinkedList();
                Object[] var4 = this.data;
                int var5 = var4.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    Object obj = var4[var6];
                    Node node = NodeFactory.create(arrayChildPath.getPath(), obj);
                    if (node != null) {
                        nodes.add(node);
                    }
                }

                return nodes;
            } else {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    public int getElementSize() {
        return this.data == null ? 0 : this.data.length;
    }

    public String getStringElementValue(int index) {
        Object value = this.data[index];
        return value instanceof String ? (String) value : null;
    }

    public void setStringElementValue(int index, String elementValue) {
        this.data[index] = elementValue;
    }
}