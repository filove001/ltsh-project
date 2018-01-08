package com.ltsh.common.client.redis;





import com.ltsh.common.client.spring.SpringContextUtils;
import com.ltsh.common.util.JsonUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


/**
 * Redis缓存辅助类
 * 
 * @author SameOne
 * @version 2016年4月2日 下午4:17:22
 */
public final class RedisUtil {
    private RedisUtil() {
    }
    private static ShardedJedisPool shardedJedisPool;
    private static ShardedJedis shardedJedis;
    private static Integer EXPIRE = 172800;


    public static ShardedJedis getShardedJedis(){
        shardedJedis = getShardedJedisPool().getResource();
        return shardedJedis;
    }

    public static ShardedJedisPool getShardedJedisPool() {
        if(shardedJedisPool == null) {
            shardedJedisPool = (ShardedJedisPool) SpringContextUtils.getBean("shardedJedisPool");
        }
        return shardedJedisPool;
    }

    public static final String get(final String key) {
        try (ShardedJedis shardedJedis = getShardedJedis();){
//            expire(key, EXPIRE, shardedJedis);
            return shardedJedis.get(key);
        }
    }
    /**
     * 获取时 自定义重置有效期
     * 
     * @param key
     * @param expireSeconds
     * @return
     */
    public static final String get(final String key,int expireSeconds) {
        try (ShardedJedis shardedJedis = getShardedJedis();) {
            expire(key, expireSeconds, shardedJedis);
            return shardedJedis.get(key);
        }
    }


    public static final void set(final String key, final String value, int seconds) {
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.set(key, value);
            expire(key, seconds, shardedJedis);
        }
    }
    public static final void set(final String key, final Object value, int seconds) {
        try (ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.set(key, JsonUtils.toJson(value));
            expire(key, seconds, shardedJedis);
        }
    }
    public static final void set(final String key, final String value) {
        try (ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.set(key, value);
//            expire(key, EXPIRE, shardedJedis);
        }

    }

    public static final Boolean exists(final String key) {
        try (ShardedJedis shardedJedis = getShardedJedis();) {
            return shardedJedis.exists(key);
        }
    }

    public static final void del(final String key) {
        try (ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.del(key);
        }
    }


    public static final String type(final String key) {
        try (ShardedJedis shardedJedis = getShardedJedis();) {
            expire(key, EXPIRE, shardedJedis);
            return shardedJedis.type(key).getClass().getName();
        }
    }

    /**
     * 在某段时间后失效
     * 
     * @return
     */
    public static final Long expire(final String key, final int seconds, ShardedJedis shardedJedis) {
        if(shardedJedis == null) {
            try (ShardedJedis shardedJedis1 = getShardedJedis();) {
                return shardedJedis1.expire(key, seconds);
            }
        } else {
            return shardedJedis.expire(key, seconds);
        }

    }

    public static final long ttl(final String key){
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            return shardedJedis.ttl(key);
        }
    }
    
    /**
     * 自增操作（默认加1）
     * 
     * @param key
     * @param expire
     * @return
     */
    public static long increment(String key,int expire){
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            long id = shardedJedis.incr(key);
            expire(key, expire, shardedJedis);
            return id;
        }
    }
    
    /**
     * 自增操作（默认加1）
     *
     * @return
     */
    public static long increment(String key){
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            return shardedJedis.incr(key);
        }
    }
    
    // 未完，待续...


    /**
     * 保存缓存
     * @param key
     * @param value
     * @param seconds
     */
    public static final void setCache(final String key, final Object value, Integer seconds) {
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.set(key, JsonUtils.toJson(value));
            if(seconds != null) {
                expire(key, seconds, shardedJedis);
            }
        }
    }

    /**
     * 保存缓存
     * @param key
     * @param value
     * @param seconds
     */
    public static final void setSerialize(final byte[] key, final byte[] value, Integer seconds) {
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.set(key, value);
            if(seconds != null) {
                shardedJedis.expire(key, seconds);
            }
        }
    }
    /**
     * 获取缓存
     * @param key

     */
    public static final byte[] getSerialize(final byte[] key) {
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            return shardedJedis.get(key);
        }
    }
    /**
     * 保存缓存
     * @param key
     * @param value
     * @param seconds
     */
    public static final void setCache(final String key, final String value, Integer seconds) {
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            shardedJedis.set(key, value);
            if(seconds != null) {
                expire(key, seconds, shardedJedis);
            }
        }
    }
    /**
     * 获取缓存
     * @param key
     */
    public static final String getCache(final String key) {
        try(ShardedJedis shardedJedis = getShardedJedis();) {
            return shardedJedis.get(key);
        }
    }
}
