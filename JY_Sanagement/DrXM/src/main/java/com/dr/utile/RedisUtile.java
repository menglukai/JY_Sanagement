package com.dr.utile;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @描述 redis缓存技术
 * @创建人 zlc
 * @创建时间 2019/11/16 20:40
 * @修改人和其它信息
 */

public class RedisUtile {
    static RedisTemplate<String, Object> redis ;
//加载spring参数
    public RedisUtile(RedisTemplate<String, Object> redisTemplate) {
        this.redis = redisTemplate;
    }
//向redis中传值
    public static void put(String key, Object value) {
        redis.opsForValue().set(key, value);
    }
//通过key获取value
    public static <T> T getdata(String key) {
        return (T) redis.opsForValue().get(key);
    }
    //通过key删除信息
    public static void delete(String key) {
     redis.opsForValue().getOperations().delete(key);
    }
}
