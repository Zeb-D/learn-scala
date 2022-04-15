package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.path.ChildPath;
import com.yd.scala.hello.handler.path.ObjectChildPath;
import com.yd.scala.hello.handler.path.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ObjectNode extends AbstractFieldNode<Object> {
    private static Logger logger = LoggerFactory.getLogger(ObjectNode.class);

    public static ObjectNode create(Path path, Object data) {
        return new ObjectNode(path, data);
    }

    public ObjectNode(Path path, Object data) {
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
                    Object field = this.getFieldValue(path.getName());
                    if (field != null) {
                        Node node = NodeFactory.create(path, field);
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
        Object fieldValue = this.getFieldValue(fieldName);
        return !(fieldValue instanceof String) ? null : (String) fieldValue;
    }

    public void setStringFieldValue(String fieldName, String fieldValue) {
        try {
            Method method = this.data.getClass().getMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
            method.setAccessible(true);
            method.invoke(this.data, fieldValue);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var4) {
            logger.info("设置属性值失败，fieldName={},fieldValue, object={}", new Object[]{fieldName, fieldValue, this.data, var4});
        }

    }

    private Object getFieldValue(String name) {
        try {
            Method method = this.data.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
            method.setAccessible(true);
            return method.invoke(this.data);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException var3) {
            logger.info("获取属性值失败，fieldName={}, object={}", new Object[]{name, this.data, var3});
            return null;
        }
    }
}