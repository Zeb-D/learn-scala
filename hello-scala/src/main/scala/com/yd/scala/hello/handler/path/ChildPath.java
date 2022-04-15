package com.yd.scala.hello.handler.path;

public interface ChildPath {
    boolean isArray();

    ArrayChildPath toArrayChildPath();

    ObjectChildPath toObjectChildPath();
}
