package com.yd.cache.test.config;

import com.yd.cache.threadlocal.ThreadLocalCacheLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrintLocalCacheLoader implements ThreadLocalCacheLoader<String, String> {

    @Override
    public String get(String s) {
        log.info("获取了一个本地内存值");
        //模拟其他服务调用
        return s + "---------->" + System.currentTimeMillis();
    }

    @Override
    public String getType() {
        return PrintLocalCacheLoader.class.getName();
    }
}