package com.yd.api.model.domain.device;

/**
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-30 15:30
 */
public class DeviceToken {

    // 当前可用区，当前支持：AY EU US
    private String region;
    // 配网token,配网sdk中需要将三个参数组装成一个token传给设备，设备会进行拆解
    private String token;
    // 密钥
    private String secret;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getDeviceTokenToApp(){
        return region + token + secret;
    }
}
