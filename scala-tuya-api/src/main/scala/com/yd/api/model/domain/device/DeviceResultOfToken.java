package com.yd.api.model.domain.device;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-30 15:56
 */
public class DeviceResultOfToken {

    // 配网成功设备列表
    @JSONField(name = "successDevices")
    private List<SuccessDevices> successDevices;

    // 配网失败设备列表
    @JSONField(name = "errorDevices")
    private List<ErrorDevices> errorDevices;

    public List<SuccessDevices> getSuccessDevices() {
        return successDevices;
    }

    public void setSuccessDevices(List<SuccessDevices> successDevices) {
        this.successDevices = successDevices;
    }

    public List<ErrorDevices> getErrorDevices() {
        return errorDevices;
    }

    public void setErrorDevices(List<ErrorDevices> errorDevices) {
        this.errorDevices = errorDevices;
    }
}
