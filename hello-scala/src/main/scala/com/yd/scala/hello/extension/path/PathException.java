package com.yd.scala.hello.extension.path;

/**
 * @author Zeb灬D
 * @date 2021/5/18 5:32 下午
 */

public class PathException extends RuntimeException{
    private static final long serialVersionUID = -5826535670725769867L;

    public PathException(Inode inode, String msg){
        super(inode.toString() + " " + msg);
    }

    public PathException(String msg){
        super(msg);
    }

    public PathException(Inode inode, Exception exception){
        super(inode.toString(),exception);
    }

    public PathException(String path, Exception exception){
        super(path,exception);
    }
}
