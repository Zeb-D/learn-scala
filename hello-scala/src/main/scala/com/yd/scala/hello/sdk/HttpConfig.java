package com.yd.scala.hello.sdk;

/**
 * @author created by Zeb灬D on 2021-07-23 13:12
 */
public class HttpConfig {
    public static final int DEFAULT_THREADS_PER_ACCESS_ID = 200;

    public static final int DEFAULT_TIMEOUT_SECONDS = 15;
    /**
     * Http并发线程数
     */
    public int threads = DEFAULT_THREADS_PER_ACCESS_ID;

    /**
     * 连接超时时间，单位：秒
     */
    public int connectionTimeout = DEFAULT_TIMEOUT_SECONDS;

    /**
     * 读超时时间，单位：秒
     */
    public int readTimeout = DEFAULT_TIMEOUT_SECONDS;

    /**
     * 写超时时间，单位：秒
     */
    public int writeTimeout = DEFAULT_TIMEOUT_SECONDS;

}
