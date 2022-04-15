package com.yd.scala.hello.handler.path;

public class ArrayChildPath implements ChildPath {
    private Path path;

    @Override
    public boolean isArray() {
        return true;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    @Override
    public ArrayChildPath toArrayChildPath() {
        return this;
    }

    @Override
    public ObjectChildPath toObjectChildPath() {
        throw new RuntimeException("ArrayChildPath不能转换为ObjectChildPath类型");
    }
}
