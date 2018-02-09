package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.RedisService;
import com.ltsh.chat.service.utils.RedisConfig;
import com.ltsh.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;


import java.util.concurrent.TimeUnit;

/**
 * Created by fengjianbo on 2018/2/6.
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, final String value, Long seconds) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return true;
            }
        });
        if(seconds != null) {
            expire(key, seconds);
        }
        return result;
    }
    /**
     * 设置缓存,
     * @param key
     * @param value
     * @return
     */
    public <T> boolean set(final String key, final T value, Long seconds) {
        String strValue = JsonUtils.toJson(value);
        return set(key, strValue, seconds);
    }
    /**
     * 获取缓存
     * @param key
     * @return
     */
    public <T> T get(final String key, Class<T> classT) {
        String value = get(key);
        return JsonUtils.fromJson(value, classT);
    }
    /**
     * 获取缓存
     * @param key
     * @return
     */
    public String get(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }
    /**
     * 获取并删除缓存
     * @param key
     * @return
     */
    public String getAndDel(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                redisTemplate.delete(key);
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean del(final String key) {
        Boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                redisTemplate.delete(key);
                return true;
            }
        });
        return result;
    }
    /**
     * 设置过期时间
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(final String key,long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }
}
