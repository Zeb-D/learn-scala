package com.yd.scala.hello.extension.config;

import java.io.Serializable;

/**
 * @author Zeb灬D
 * @date 2021/5/7 3:48 下午
 */

public class ExtensionResult<T> implements Serializable {


    private static final long serialVersionUID = 5142461222290957406L;
    /**
     * 插件处理结果
     */
    private boolean success;
    /**
     * 错误描述信息
     */
    private String	errorMsg;
    /**
     * 异常对象
     */
    private Throwable exception;
    /**
     * fnahui
     */
    private T result;

    public static <T> ExtensionResult<T> success(T result){
        ExtensionResult<T> extensionResult = new ExtensionResult<>();
        extensionResult.setResult(result);
        return extensionResult;
    }

    public static <T> ExtensionResult<T> exception(Throwable exception){
        ExtensionResult<T> extensionResult = new ExtensionResult<>();
        extensionResult.setException(exception);
        return extensionResult;
    }

    public static <T> ExtensionResult<T> error(String errorMsg){
        ExtensionResult<T> extensionResult = new ExtensionResult<>();
        extensionResult.setErrorMsg(errorMsg);
        return extensionResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.success = false;
        this.errorMsg = errorMsg;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.success = false;
        this.exception = exception;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.success = true;
        this.result = result;
    }
}
