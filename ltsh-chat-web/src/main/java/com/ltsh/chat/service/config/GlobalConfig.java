package com.ltsh.chat.service.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.*;

/**
 * Created by fengjianbo on 2018/1/9.
 */
@Component
@Data
public class GlobalConfig extends PropertyPlaceholderConfigurer {

    private static Logger logger = LoggerFactory.getLogger(GlobalConfig.class);
    private static final byte[] KEY = {9, -1, 0, 5, 39, 8, 6, 19};
    private static Map<String, String> ctxPropertiesMap = new HashMap<String, String>();
    private List<String> decryptProperties;

    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
//        ctxPropertiesMap = new HashMap<String, String>();
        if(props.getProperty("config.path") != null) {
            props.load(new FileInputStream(props.getProperty("config.path")));
        }
        CharsetEncoder charsetDecoder = Charset.forName("GBK").newEncoder();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            if(value!=null && !"".equals(value.trim()) && !(charsetDecoder.canEncode(value))){
                value = new String(value.getBytes("iso-8859-1"),"UTF-8");
            }
            ctxPropertiesMap.put(keyStr, value);
        }
//        logger.info("加载配置成功配置如下:{}", JsonUtil.toJson(ctxPropertiesMap));
    }

    /**
     * @param decryptProperties the decryptPropertiesMap to set
     */
    public void setDecryptProperties(List<String> decryptProperties) {
        this.decryptProperties = decryptProperties;
    }

    /**
     * Get a value based on key , if key does not exist , null is returned
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        try {
            return ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return null;
        }
    }

    /**
     * Get a value based on key , if key does not exist , null is returned
     *
     * @param key
     * @return
     */
    public static String getString(String key,String defaultValue) {
        try {
            return ctxPropertiesMap.get(key);
        } catch (MissingResourceException e) {
            return defaultValue;
        }
    }
    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public static int getInt(String key) {
        return Integer.parseInt(ctxPropertiesMap.get(key));
    }

    /**
     * 根据key获取值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }

    public static long getLong(String key) {
        return Long.parseLong(ctxPropertiesMap.get(key));
    }

    public static long getLong(String key, long defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }
    /**
     * 根据key获取值
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = ctxPropertiesMap.get(key);
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return new Boolean(value);
    }
}
