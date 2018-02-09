package com.ltsh.chat.service.api;


/**
 * Created by fengjianbo on 2018/2/6.
 */
public interface RedisService {
    /**
     * 设置缓存
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, final String value, Long seconds);
    /**
     * 设置缓存,
     * @param key
     * @param value
     * @return
     */
    public <T> boolean set(final String key, final T value, Long seconds);
    /**
     * 获取缓存
     * @param key
     * @return
     */
    public <T> T get(final String key, Class<T> classT);
    /**
     * 获取缓存
     * @param key
     * @return
     */
    public String get(final String key);

    /**
     * 获取并删除缓存
     * @param key
     * @return
     */
    public String getAndDel(final String key);

    /**
     * 删除缓存
     * @param key
     * @return
     */
    public boolean del(final String key);
    /**
     * 设置过期时间
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(final String key,long expire);


}
