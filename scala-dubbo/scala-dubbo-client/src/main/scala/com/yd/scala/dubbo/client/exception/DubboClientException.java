package com.yd.scala.dubbo.client.exception;

/**
 * @author created by Zeb灬D on 2020-05-30 13:48
 */
public class DubboClientException extends RuntimeException {
    public DubboClientException() {
    }

    public DubboClientException(String message) {
        super(message);
    }

    public DubboClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public DubboClientException(Throwable cause) {
        super(cause);
    }

    public DubboClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
