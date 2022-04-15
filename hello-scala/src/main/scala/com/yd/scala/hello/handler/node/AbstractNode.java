package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.path.Path;

public abstract class AbstractNode implements Node {
    protected Path path;

    public AbstractNode() {
    }

    public boolean isElement() {
        return this.path.getLeaf().isElement();
    }

    public Path getPath() {
        return this.path;
    }

    public boolean hasLeaf() {
        return this.path.hasLeaf();
    }

    public boolean hasChild() {
        return this.path.hasChild();
    }
}