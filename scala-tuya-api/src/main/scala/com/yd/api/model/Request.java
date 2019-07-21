package com.yd.api.model;


import com.yd.api.model.enums.HttpMethod;

import java.util.Map;

/**
 *
 * 常见的http请求体
 *
 * @author: gongjin[gongjin@tuya.com]
 * @date: 2019-01-10 22:13
 */
public class Request {

    private String url;
    private HttpMethod method;
    private Map<String, String> urlParams;
    private Boolean isToken;
    private Object body;
    private BaseInfo baseInfo;

    public Request(String url, HttpMethod method, Map<String, String> urlParams, Boolean isToken, Object body, BaseInfo baseInfo) {
        this.url = url;
        this.method = method;
        this.urlParams = urlParams;
        this.isToken = isToken;
        this.body = body;
        this.baseInfo = baseInfo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Map<String, String> getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(Map<String, String> urlParams) {
        this.urlParams = urlParams;
    }

    public Boolean getToken() {
        return isToken;
    }

    public void setToken(Boolean token) {
        isToken = token;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

}
