package com.yd.api.model.domain.device;

/**
 * 配网成功设备
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-30 15:49
 */
public class SuccessDevices {

    // 设备id
    private String id;
    // 产品id
    private String productId;
    // 设备名称
    private String name;
    // 在线状态
    private Boolean isOnline;
    // 经度
    private String lon;
    // 纬度
    private String lat;
    // ip
    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
