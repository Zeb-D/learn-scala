package com.yd.scala.hello.handler.node;

import com.yd.scala.hello.handler.path.Path;

import java.util.List;

public interface Node {
    Path getPath();

    boolean hasLeaf();

    boolean hasChild();

    boolean isElement();

    List<Node> getChildren();

    List<String> getFields();

    String getStringFieldValue(String var1);

    void setStringFieldValue(String var1, String var2);

    int getElementSize();

    String getStringElementValue(int var1);

    void setStringElementValue(int var1, String var2);
}