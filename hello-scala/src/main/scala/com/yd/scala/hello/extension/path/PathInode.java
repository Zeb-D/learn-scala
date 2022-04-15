package com.yd.scala.hello.extension.path;

/**
 * @author Zeb灬D
 * @date 2021/5/18 10:40 上午
 */

public class PathInode {
    private boolean hasError;
    private Inode inode;

    public boolean hasError() {
        return hasError;
    }

    public void setError() {
        this.hasError = true;
    }

    public Inode getInode() {
        return inode;
    }

    public void setInode(Inode inode) {
        this.inode = inode;
    }
}
