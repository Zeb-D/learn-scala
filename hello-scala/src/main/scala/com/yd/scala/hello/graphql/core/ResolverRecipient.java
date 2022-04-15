package com.yd.scala.hello.graphql.core;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author created by Zeb灬D on 2021-09-15 17:46
 */
@Data
@NoArgsConstructor
public class ResolverRecipient {
    /**
     * 唯一的resolver标识
     */
    private String id;

    /**
     * 支持dubbo, http, jvm
     */
    private String proto;
    private String name;
    private String description;

    /**
     * dubbo jvm 协议时调用的类
     */
    private String interfaceClass;

    /**
     * dubbo jvm 协议指明调用的方法
     */
    private String method;

    /**
     * 固定参数, key为属性名, value 为值
     */
    private Map<String, Object> variables;

    /**
     * http的请求头
     */
    private Map<String, Object> headers;

    /**
     * 参数名
     */
    private List<Map<String, Object>> arguments;

    /**
     * http协议请求的host
     */
    private String host;

    /**
     * http协议时请求的uri
     */
    private String uri;

    /**
     *
     */
    private String body;

    /**
     * dubbo attachments
     */
    private Map<String, Object> attachments;

    /**
     * dubbo 版本
     */
    private String version;

    private String decode;
}
