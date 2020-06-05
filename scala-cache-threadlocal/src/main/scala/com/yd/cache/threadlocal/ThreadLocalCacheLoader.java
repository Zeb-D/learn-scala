package com.yd.cache.threadlocal;

/**
 * ThreadLocalCache 本地缓存数据加载器，当缓存中没有数据时，会调用此类加载数据。
 *
 * @param <ID> 数据ID
 * @param <T>  数据类型
 * @author created by Zeb-D on 2019-05-08 14:37
 */
public interface ThreadLocalCacheLoader<ID, T> {

    /**
     * 根据实体ID获取数据对象。
     * 可以考虑当前线程多次请求，可能导致缓存穿透
     *
     * @param id
     * @return
     */
    T get(ID id);

    /**
     * 获取缓存类型，相当于Map的key 需要唯一
     *
     * @return
     */
    String getType();

}