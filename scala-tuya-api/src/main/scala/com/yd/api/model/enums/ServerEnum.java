package com.yd.api.model.enums;

/**
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-07 10:40
 */
public enum ServerEnum {
    CN_ONLINE("https://openapi.tuyacn.com"),
    EU_ONLINE("https://openapi.tuyaeu.com"),
    US_ONLINE("https://openapi.tuyaus.com"),
    ;

    private String url;

    ServerEnum(String url) {
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }
}
