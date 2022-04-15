package com.yd.scala.hello.extension.exception;

/**
 * Created By Zeb灬D On 2020-03-28
 **/

public class ExtensionStateException extends RuntimeException {

    private static final long serialVersionUID = -1046951269981870920L;

    public ExtensionStateException(String message) {
        super(message);
    }

    public ExtensionStateException(Throwable cause) {
        super(cause);
    }

    public ExtensionStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
