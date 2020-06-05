package com.yd.cache.threadlocal;

import javafx.util.Pair;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 提供便捷的上下文
 *
 * @author created by Zeb-D on 2019-05-08 14:37
 */
@Component
@Slf4j
public class ThreadLocalCacheContext implements ApplicationContextAware {

    private static final ThreadLocal<ThreadLocalCache> THREAD_LOCAL = new ThreadLocal<ThreadLocalCache>() {
        @Override
        protected ThreadLocalCache initialValue() {
            return new ThreadLocalCache(THREAD_LOCAL_CACHE_LOADERS);
        }
    };

    private static final Map<String, ThreadLocalCacheLoader<Object, Object>> THREAD_LOCAL_CACHE_LOADERS = new HashMap<>();

    /**
     * 根据缓存类型和实体ID获取数据。
     *
     * @param cacheKey
     * @param id
     * @return
     */
    public static <T> T get(String cacheKey, @NonNull Object id) {
        Pair<String, Object> pair = new Pair<>(cacheKey, id);
        return THREAD_LOCAL.get().get(pair);
    }

    /**
     * 根据缓存类型和实体ID设置缓存数据。
     *
     * @param cacheKey
     * @param id
     * @param data
     */
    public static void put(String cacheKey, Object id, Object data) {
        Pair pair = new Pair(cacheKey, id);
        THREAD_LOCAL.get().put(pair, data);
    }

    /**
     * 根据缓存类型和实体ID移除缓存数据。
     *
     * @param cacheKey
     * @param id
     */
    public static void remove(String cacheKey, Object id) {
        Pair pair = new Pair(cacheKey, id);
        THREAD_LOCAL.get().remove(pair);
    }

    /**
     * 清理ThreadLocalCache，避免脏数据和内存泄漏。
     */
    public static void cleanUp() {
        log.debug("Exec ThreadLocalCache cleanUp.");

        THREAD_LOCAL.set(null);
        THREAD_LOCAL.remove();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        registerThreadLocalLoaders(applicationContext);
    }

    /**
     * 查找并注册数据缓存加载器。
     *
     * @param applicationContext
     */
    private void registerThreadLocalLoaders(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(ThreadLocalCacheLoader.class)
                .forEach((k, v) -> THREAD_LOCAL_CACHE_LOADERS.put(v.getType(), v));
    }

}