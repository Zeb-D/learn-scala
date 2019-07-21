package com.yd.api.model.domain.device;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-30 18:05
 */
public class DeviceVo {
    // 设备编号
    private String id;
    // 用户id
    private String uid;
    // 密钥
    @JSONField(name = "local_key")
    private String localKey;
    // 产品类别
    private String category;
    // 产品id
    @JSONField(name = "product_id")
    private String productId;
    // 是否是子设备（true：是，false：不是）
    private Boolean sub;
    // 设备唯一标识
    private String uuid;
    // 设备拥有者id
    @JSONField(name = "owner_id")
    private String ownerId;
    // 设备在线状态
    private Boolean online;
    // 设备名称
    private String name;
    // ip地址
    private String ip;
    // 时区
    @JSONField(name = "time_zone")
    private String timeZone;
    // 设备初次配网时间
    @JSONField(name = "create_time")
    private Long createTime;
    // 设备状态更新时间
    @JSONField(name = "update_time")
    private Long updateTime;
    // 设备上次配网时间
    @JSONField(name = "active_time")
    private String activeTime;
    // 设备功能状态
    private List<Status> status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocalKey() {
        return localKey;
    }

    public void setLocalKey(String localKey) {
        this.localKey = localKey;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Boolean getSub() {
        return sub;
    }

    public void setSub(Boolean sub) {
        this.sub = sub;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }
}
