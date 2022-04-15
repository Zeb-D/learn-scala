package com.yd.scala.hello.handler.path;

import com.yd.scala.hello.handler.leaf.Leaf;

public class Path {
    private final Path parent;
    private final String name;
    private String longName = null;
    private ChildPath childPath = null;
    private Leaf leaf = null;

    public Path(Path parent, String name) {
        this.parent = parent;
        this.name = name;
    }


    public Path getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public boolean hasLeaf() {
        return leaf != null;
    }

    public Leaf getLeaf() {
        return leaf;
    }

    public void setLeaf(Leaf leaf) {
        this.leaf = leaf;
    }

    public boolean hasChild() {
        return childPath != null;
    }

    public ChildPath getChild() {
        return childPath;
    }

    public void setChild(ChildPath childPath) {
        this.childPath = childPath;
    }

    public String getLongName() {
        return longName;
    }

    @Override
    public String toString() {
        if (longName != null) {
            return longName;
        }
        if (parent != null) {
            longName = parent.toString() + "." + name;
        } else {
            longName = name;
        }
        return longName;
    }
}
