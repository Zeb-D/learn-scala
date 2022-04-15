package com.yd.scala.hello.extension.path;


import org.apache.dubbo.common.utils.StringUtils;

public class Inode {
    /**
     * 内嵌参数
     */
    private Inode next;
    /**
     * 参数名
     */
    private String name;
    /**
     * 长路径参数
     */
    private String longName;
    /**
     * 标示符
     */
    private String key;
    /**
     * 列表下标
     */
    private int index = -1;

    public Inode(String name) {
        this.name = name;
    }

    public Inode(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public Inode() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Inode getNext() {
        return next;
    }

    public boolean hasKey() {
        return !StringUtils.isBlank(key);
    }

    public boolean hasIndex() {
        return index >= 0;
    }

    public boolean hasNext() {
        return next != null;
    }

    public void setNext(Inode next) {
        this.next = next;
    }

    public Inode next() {
        return next;
    }

    @Override
    public String toString() {
        return longName;
    }
}
