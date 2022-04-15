package com.yd.api.model.enums;

public enum HttpMethod {
    POST("post","post方法"),
    GET("get","get方法"),
    PUT("put","put方法"),
    DELETE("delete","delete方法")
    ;

    private String name;
    private String desc;

    HttpMethod(String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
