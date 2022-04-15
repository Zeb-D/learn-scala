package com.yd.scala.hello.extension.exception;

/**
 * Created By Zeb灬D On 2020-03-28
 **/

public class ExtensionRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 6797670761049870669L;

    public ExtensionRuntimeException(String message) {
        super(message);
    }

    public ExtensionRuntimeException(Throwable cause) {
        super(cause);
    }

    public ExtensionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
