package org.ltsh.common.client.redis;

/**
 * Created by Random on 2017/10/11.
 */
public class RedisKey {
    public static final String TOKEN_KEY = "org:ltsh:chat:user:tokenKey:";
    public static final String RANDOM_KEY = "org:ltsh:chat:user:random:";
    public static String getRedisKey(String packageName,String ... keys) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(packageName);
        for (String key : keys) {
            buffer.append(key).append("_");
        }
        return buffer.substring(0, buffer.length() - 1);
    }
}
