package com.yd.api.exception;

/**
 *
 * @date: 2019-01-07 14:45
 */
public class TuyaClientException extends RuntimeException {

    private Integer Code;

    public TuyaClientException(String msg) {
        super(msg);
    }

    public TuyaClientException(Integer code, String msg){
        super(msg);
        this.Code = code;
    }

    public Integer getCode() {
        return Code;
    }
}
