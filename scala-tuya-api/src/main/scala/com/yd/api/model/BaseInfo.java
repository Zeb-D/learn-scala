package com.yd.api.model;


import com.yd.api.model.enums.RegionEnum;

/**
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-07 20:51
 */
public class BaseInfo {

    private String accessToken = "";
    private String refreshToken = "";
    private String secret = "";
    private String clientId = "";
    private String baseUrl = "";
    private RegionEnum regionType = RegionEnum.CN;
    private final String contentType = "application/json";
    private String code = "";

    public BaseInfo(String secret, String clientId, RegionEnum regionType, String code) {
        this.secret = secret;
        this.clientId = clientId;
        this.regionType = regionType;
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public RegionEnum getRegionType() {
        return regionType;
    }

    public void setRegionType(RegionEnum regionType) {
        this.regionType = regionType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
