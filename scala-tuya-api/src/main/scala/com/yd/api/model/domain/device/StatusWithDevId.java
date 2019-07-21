package com.yd.api.model.domain.device;

import java.util.List;

/**
 * 将API返回的map进行转换
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-02-10 17:04
 */
public class StatusWithDevId {

    private String devId;

    private List<Status> statusList;

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public List<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Status> statusList) {
        this.statusList = statusList;
    }
}
