package com.yd.scala.hello.dao;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

@TableName("t_user")
public class User implements Serializable {
    private String id;
    private String userName;
    private String userPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}