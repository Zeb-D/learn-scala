package com.yd.scala.hello.handler.utils;

import java.util.concurrent.ConcurrentHashMap;

public class DataPool {

    private final static ConcurrentHashMap<String, Object> DATA_POLL = new ConcurrentHashMap<>();

    public static void addStrategy(String name, Object data) {
        DATA_POLL.put(name, data);
    }


    public static Object get(String key) {
        return DATA_POLL.get(key);
    }


}
