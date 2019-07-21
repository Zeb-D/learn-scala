package com.yd.api.model.domain.oauth;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Token 接口所返回的参数
 *
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-21 19:57
 */
public class TokenResult implements Serializable {

    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expire_time")
    private String expireTime;
    @JSONField(name = "refresh_token")
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
