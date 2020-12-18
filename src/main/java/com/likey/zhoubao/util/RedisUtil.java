package com.likey.zhoubao.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description: Redis工具类
 * @author: likeyu
 * @create: 2020-12-03 15:43
 **/
@Component
public class RedisUtil {

    //单位：分钟
    private static final long TOKEN_TIME_OUT_COUNT = 30;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private boolean remove(String key) {
        return redisTemplate.delete(key);
    }


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    private void set(final String key, final String value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key     键
     * @param timeout 时间(秒)
     * @return
     */
    private Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    public void putUserToken(String token, String userInfo) {
        this.set(token, userInfo, TOKEN_TIME_OUT_COUNT, TimeUnit.MINUTES);
    }

    public String getUserToken(String token) {
        return (String) this.get(token);
    }

    /**
     * @Description 重置token的过期时间
     * @Date 2020/12/3
     */
    public void expireToken(String tokenKey) {
        expire(tokenKey, TOKEN_TIME_OUT_COUNT, TimeUnit.MINUTES);
    }

    /*
     * 获取过期时间
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

}
