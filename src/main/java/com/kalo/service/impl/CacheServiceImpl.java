package com.kalo.service.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kalo.service.CacheService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Kalo
 * @create 2020-05-01 14:53
 */
@Service
public class CacheServiceImpl implements CacheService {

    private Cache<String, Object> cache = null;

    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder()
                .initialCapacity(10)
                .maximumSize(100)
                // 设置写缓存后过期时间
                .expireAfterWrite(2, TimeUnit.HOURS)
                .build();
    }

    @Override
    public boolean setCache(String key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return false;
        }
        try {
            cache.put(key, value);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object getCache(String key) {
        return cache.getIfPresent(key);
    }
}
