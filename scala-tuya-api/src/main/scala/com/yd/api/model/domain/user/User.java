package com.yd.api.model.domain.user;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * tuya cloudy api 用户模型
 *
 * @date: 2019-01-25 17:03
 */
public class User implements Serializable {

    // 用户id
    private String uid;
    // 用户名
    @JSONField(name = "username")
    private String userName;
    // 国家码
    @JSONField(name = "country_code")
    private String countryCode;
    // 邮箱
    private String email;
    // 手机号
    private String mobile;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
