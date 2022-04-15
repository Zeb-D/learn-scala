package com.yd.scala.hello.handler.path;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectChildPath implements ChildPath {
    private final Map<String, Path> pathMap = new ConcurrentHashMap<>();

    @Override
    public boolean isArray() {
        return false;
    }

    public Path getPath(String name) {
        return pathMap.get(name);
    }

    public void putPath(String name, Path path) {
        pathMap.put(name, path);
    }

    public List<Path> getPaths() {
        return new LinkedList<>(pathMap.values());
    }

    @Override
    public ArrayChildPath toArrayChildPath() {
        throw new RuntimeException("ObjectChildPath不能转换为ArrayChildPath类型");
    }

    @Override
    public ObjectChildPath toObjectChildPath() {
        return this;
    }
}
