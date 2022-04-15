package com.yd.api.model.domain.device;

import java.util.List;

/**
 *
 * @date: 2019-02-10 16:26
 */
public class BatchDevices {

    // 总数
    private Long total;

    // 设备列表
    private List<DeviceVo> devices;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<DeviceVo> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceVo> devices) {
        this.devices = devices;
    }
}
