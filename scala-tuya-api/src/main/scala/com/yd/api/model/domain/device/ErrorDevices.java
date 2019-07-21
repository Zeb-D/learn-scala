package com.yd.api.model.domain.device;

/**
 * 配网失败设备
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-30 15:56
 */
public class ErrorDevices {

    // 设备id
    private String id;
    // 失败错误码
    private String errorCode;
    // 失败错误描述
    private String errorMsg;
    // 设备名称
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
