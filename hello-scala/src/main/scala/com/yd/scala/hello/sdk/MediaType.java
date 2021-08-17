package com.yd.scala.hello.sdk;

public enum MediaType {
    /**
     * 表单类型
     */
    XForm("application/x-www-form-urlencoded"),

    /**
     * JSON类型
     */
    Json("application/json"),

    /**
     * none类型，即只通过url传参
     */
    None("none");


    private String text;

    MediaType(String text) {
        this.text = text;
    }
}
