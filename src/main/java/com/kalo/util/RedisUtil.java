package com.kalo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Kalo
 * @create 2020-04-30 19:32
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置缓存失效时间
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        if (StringUtils.isEmpty(key) || time < 0) {
            return false;
        }
        try {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取key的过期时间
     * @param key
     * @return
     */
    public long getExpire(String key) {
        if (StringUtils.isEmpty(key)) {
            return 0;
        }
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * key为String类型的获取缓存
     * @param key
     * @return
     */
    public Object get(String key) {
        return StringUtils.isEmpty(key) ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * key为String类型的设置缓存，没有设置过期时间
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * key为String类型的设置缓存，可以设置过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public boolean set(String key, Object value, long time) {
        if (StringUtils.isEmpty(key) || value == null) {
            return false;
        }
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                return true;
            }
            return set(key,value);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Hash类型的获取对应key的对应item的value
     * @param key
     * @param item
     * @return
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key,item);
    }

    /**
     * Hash类型获取对应key的所有键值
     * @param key
     * @return
     */
    public Map<Object, Object> hgetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Hash类型单个存储
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hset(String key, String item, Object value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(item) || value == null) {
            return false;
        }
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hash类型，可以设置过期时间的单个存储
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean hset(String key, String item, Object value, long time) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(item) || value == null) {
            return false;
        }
        try {
            if (hset(key, item, value)) {
                if (time > 0) {
                    expire(key, time);
                }
                return true;
            }
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hash类型，存储一个集合
     * @param key
     * @param map
     * @return
     */
    public boolean hsetAll(String key, Map<?, ?> map) {
        if (StringUtils.isEmpty(key) || map == null) {
            return false;
        }
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Hash类型，可以设置过期时间的存储一个集合
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean hsetAll(String key, Map<Object, Object> map, long time) {
        if (StringUtils.isEmpty(key) || map == null) {
            return false;
        }
        try {
            if (hsetAll(key, map)) {
                if (time > 0) {
                    expire(key,time);
                }
                return true;
            }
            return false;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


//    ====================List========================

    /**
     * 获取缓存
     * @param key
     * @param start
     * @param end    0 - -1 代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 存储单个值
     * @param key
     * @param value
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存储list集合
     * @param key
     * @param value
     * @return
     */
    public boolean lSetAll(String key, List<?> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
