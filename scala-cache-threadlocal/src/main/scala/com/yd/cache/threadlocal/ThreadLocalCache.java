package com.yd.cache.threadlocal;


import javafx.util.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ThreadLocal 实现的本地缓存。
 *
 * @author created by Zeb-D on 2019-05-08 14:37
 */
public class ThreadLocalCache {

    private Map<Pair<String, Object>, Optional<Object>> cache;

    private Map<String, ThreadLocalCacheLoader<Object, Object>> loaders;

    public ThreadLocalCache(Map<String, ThreadLocalCacheLoader<Object, Object>> loaders) {
        this.cache = new HashMap<>(8);
        this.loaders = Optional.ofNullable(loaders).orElse(Collections.emptyMap());
    }

    /**
     * 根据缓存类型和实体ID获取数据。
     *
     * @param pair
     * @return
     */
    public <T> T get(Pair<String, Object> pair) {
        return (T) Optional.ofNullable(cache.get(pair))
                .orElseGet(() -> Optional.ofNullable(load(pair)))
                .orElse(null);

    }

    private Object load(Pair<String, Object> pair) {

        Object value = getLoader(pair.getKey()).get(pair.getValue());

        put(pair, value);

        return value;
    }

    private ThreadLocalCacheLoader<Object, Object> getLoader(String cacheKey) {

        ThreadLocalCacheLoader<Object, Object> loader = loaders.get(cacheKey);

        if (loader == null) {
            throw new IllegalArgumentException("There is no ThreadLocalCacheLoader for " + cacheKey);
        }

        return loader;
    }

    /**
     * 根据缓存类型和实体ID设置缓存数据。
     *
     * @param pair
     * @param model
     */
    public void put(Pair pair, Object model) {
        cache.put(pair, Optional.ofNullable(model));
    }

    /**
     * 根据缓存类型和实体ID移除缓存数据。
     *
     * @param pair
     */
    public void remove(Pair pair) {
        cache.remove(pair);
    }

}