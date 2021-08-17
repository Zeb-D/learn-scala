package com.yd.scala.hello.sdk;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface SdkHttp {
    /**
     * @return HttpAPI定义的路径
     */
    String path();

    /**
     * @return HttpMethod
     */
    HttpMethod method();

    /**
     * 请求体数据结构类型
     */
    MediaType requestType() default MediaType.None;

    /**
     * 响应数据类型
     */
    MediaType responseType() default MediaType.Json;
}
