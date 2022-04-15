package com.yd.scala.hello.handler.node;

import com.alibaba.fastjson.JSONArray;
import com.yd.scala.hello.handler.path.ArrayChildPath;
import com.yd.scala.hello.handler.path.ChildPath;
import com.yd.scala.hello.handler.path.Path;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class JSONArrayNode extends AbstractElementNode {
    private JSONArray data;

    public static JSONArrayNode create(Path path, JSONArray data) {
        return new JSONArrayNode(path, data);
    }

    private JSONArrayNode(Path path, JSONArray data) {
        this.data = data;
        this.path = path;
    }

    public List<Node> getChildren() {
        ChildPath childPath = this.path.getChild();
        if (childPath != null && childPath.isArray()) {
            if (this.data != null && !this.data.isEmpty()) {
                ArrayChildPath arrayChildPath = childPath.toArrayChildPath();
                List<Node> nodes = new LinkedList();
                Iterator var4 = this.data.iterator();

                while (var4.hasNext()) {
                    Object obj = var4.next();
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
        return this.data == null ? 0 : this.data.size();
    }

    public String getStringElementValue(int index) {
        Object value = this.data.get(index);
        return value instanceof String ? (String) value : null;
    }

    public void setStringElementValue(int index, String elementValue) {
        this.data.set(index, elementValue);
    }
}