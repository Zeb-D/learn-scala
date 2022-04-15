package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.path.ChildPath;
import com.yd.scala.hello.handler.path.ObjectChildPath;
import com.yd.scala.hello.handler.path.Path;

import java.util.*;

public class MapNode extends AbstractFieldNode<Map<String, Object>> {
    public static MapNode create(Path path, Map<String, Object> data) {
        return new MapNode(path, data);
    }

    private MapNode(Path path, Map<String, Object> data) {
        this.path = path;
        this.data = data;
    }

    public List<Node> getChildren() {
        ChildPath childPath = this.path.getChild();
        if (childPath != null && !childPath.isArray()) {
            if (this.data == null) {
                return Collections.emptyList();
            } else {
                ObjectChildPath objectChildPath = childPath.toObjectChildPath();
                List<Path> paths = objectChildPath.getPaths();
                List<Node> nodes = new LinkedList();
                Iterator var5 = paths.iterator();

                while (var5.hasNext()) {
                    Path path = (Path) var5.next();
                    Object value = ((Map) this.data).get(path.getName());
                    if (value != null) {
                        Node node = NodeFactory.create(path, value);
                        if (node != null) {
                            nodes.add(node);
                        }
                    }
                }

                return nodes;
            }
        } else {
            return Collections.emptyList();
        }
    }

    public String getStringFieldValue(String fieldName) {
        Object value = ((Map) this.data).get(fieldName);
        return value instanceof String ? (String) value : null;
    }

    public void setStringFieldValue(String fieldName, String fieldValue) {
        ((Map) this.data).put(fieldName, fieldValue);
    }
}