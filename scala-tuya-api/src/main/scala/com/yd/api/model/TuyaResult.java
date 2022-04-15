package com.yd.api.model;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 返回的Http对象
 *
 *
 * @date: 2019-01-21 17:57
 */
public class TuyaResult implements Serializable {
    private Integer code;
    private String msg;
    private Boolean success;
    private Long t;
    private String result;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setT(Long t) {
        this.t = t;
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Long getT() {
        return t;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
