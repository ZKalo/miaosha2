package com.kalo.service;

/**
 * @author Kalo
 * @create 2020-05-01 14:34
 */
public interface CacheService {

    /**
     * 本地缓存存储
     * @param key
     * @param value
     * @return
     */
    boolean setCache(String key, Object value);

    /**
     * 获取本地缓存
     * @param key
     * @return
     */
    Object getCache(String key);
}
