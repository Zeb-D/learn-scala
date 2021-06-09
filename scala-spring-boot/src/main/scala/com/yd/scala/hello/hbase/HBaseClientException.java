package com.yd.scala.hello.hbase;


public class HBaseClientException extends RuntimeException {
    public HBaseClientException(String msg) {
        super(msg);
    }

    public HBaseClientException(Throwable t) {
        super(t);
    }

    public HBaseClientException(String msg, Throwable t) {
        super(msg, t);
    }
}
