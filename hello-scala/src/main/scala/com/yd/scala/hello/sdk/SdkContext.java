package com.yd.scala.hello.sdk;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author created by Zeb灬D on 2021-07-23 12:54
 */
@Setter
@Getter
public class SdkContext {
    private String host;
    private String path;
    private HttpMethod method;
    private String language;

    /**
     * 用于储存请求模型定义的额外参数。
     * 除非特定协议的Encoder读取此字段的键值，否则不会主动设置到请求体中
     */
    private Map<String, Object> extras = new HashMap<>(4);

    /**
     * 用于设置请求体的Header参数键值对。
     * 所有协议的请求都会将headers的键值设置到请求中。
     */
    private Map<String, String> headers = new HashMap<>(4);

    public static final String CONTEXT_KEY_REQUEST_ID = "requestId";

    /**
     * 设置Context的RequestId。
     * 注意：RequestId需要每次请求都设置
     */
    public SdkContext setRequestId(String requestId) {
        extras.put(CONTEXT_KEY_REQUEST_ID, requestId);
        return this;
    }

    /**
     * 获取设置的RequestId
     */
    public String getRequestId() {
        return (String) extras.get(CONTEXT_KEY_REQUEST_ID);
    }


    /**
     * 添加Header
     */
    public SdkContext putHeader(String name, String value) {
        extras.put(name, value);
        return this;
    }

    /**
     * 获取Headers的副本
     *
     * @return
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

}
