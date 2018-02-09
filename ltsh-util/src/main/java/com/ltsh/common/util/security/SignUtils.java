package com.ltsh.common.util.security;


import com.ltsh.common.util.JsonUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Random on 2017/9/29.
 */
public class SignUtils {
    public static void main(String[] args) {
        RequestContext<Map<String, Object>> requestContext = new RequestContext<>();
        requestContext.setAppId("123");
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "123");
        map.put("age", 1);
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("test", "test");
        map1.put("aa", "aa");
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("test", "test");
        map2.put("aa", "aa");
        map1.put("content", map2);
        map.put("content", map1);
        requestContext.setContent(map);
        String signStr = getSignStr(requestContext);
        System.out.println(signStr);
    }
    public static String getSignStr(Object params) {
        Map map = JsonUtils.fromJson(JsonUtils.toJson(params), Map.class);
        Set<Object> strings = map.keySet();
        String[] paramsStrs = new String[strings.size()];
        strings.toArray(paramsStrs);
        Arrays.sort(paramsStrs);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < paramsStrs.length; i++) {
            String str = paramsStrs[i];
            if(str.equals("class") || str.equals("signInfo")) {
                continue;
            }
            if(map.get(str) == null) {
                continue;
            }
            if(map.get(str) instanceof Map) {
                StringBuffer sortStr = getSortStr((Map) map.get(str));
                stringBuffer.append(str).append("=");
                if(sortStr != null) {
                    stringBuffer.append(sortStr);
                } else {
                    stringBuffer.append("");
                }
            } else {
                stringBuffer.append(str).append("=").append(map.get(str));
            }

            if(i != paramsStrs.length-1) {
                stringBuffer.append("&");
            }
        }
        return stringBuffer.toString();
    }
    private static StringBuffer getSortStr(Map map) {
        
        if(map != null && map.size() > 0) {
            Set<Object> strings = map.keySet();
            String[] paramsStrs = new String[strings.size()];
            strings.toArray(paramsStrs);
            Arrays.sort(paramsStrs);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("{");
            for (int i = 0; i < paramsStrs.length; i++) {
                String str = paramsStrs[i];
                if(map.get(str) instanceof Map) {
                    StringBuffer sortStr = getSortStr((Map)map.get(str));
                    stringBuffer.append("\"").append(str).append("\"").append(":").append(sortStr);
                } else {
                    stringBuffer.append("\"").append(str).append("\"").append(":").append("\"").append(map.get(str)).append("\"");
                }

                if(i != paramsStrs.length-1) {
                    stringBuffer.append(",");
                }
            }
            stringBuffer.append("}");
            return stringBuffer;
        }

        return null;
    }
    /**
     * 获取签名,签名方式md5(md5("key1=value1&key2=value2...." + key) + random),其中参数以字段排序
     * @param str 需要生成签名的字符串
     * @param key 密钥
     * @param random 随机数
     * @return
     */
    public static String getSign(String str, String key, String random){
        if(random == null) {
            random = "";
        }
        return MD5Util.encoder(MD5Util.encoder(str + key) + random);

    }
    public static String getSign(Map<String, String> params, String key, String random){
        String paramsStr = getSignStr(params);
        return getSign(paramsStr, key, random);
    }


    public static class RequestContext<T> implements Serializable {
        /**
         * app版本
         */
        private String appVersion;
        /**
         * 设备id
         */

        private String medium;
        /**
         * appId
         */

        private String appId;
        /**
         * 设备类型,0:内部通讯,1:安卓,2:IOS,3:web端
         */

        private String mediumType;
        /**
         * 系统版本
         */
        private String systemVersion;
        /**
         * 手机厂商
         */
        private String deviceBrand;
        /**
         * 手机型号
         */
        private String systemModel;

        /**
         * 系统语言
         */
        private String systemLanguage;
        /**
         * 请求流水
         */
        private String keep;
        /**
         * 业务请求组,同一个值表示该业务隶属同一个流程
         */
        private String flowKeep;
        /**
         * 时间戳字符串
         */
        private String timestamp;
        /**
         * 签名信息
         */
        private String signInfo;
        /**
         * 登录token
         */
        private String token;


        /**
         * 随机数key
         */
        private String randomKey;
        /**
         * 请求数据
         */
        private T content;

        public String getAppVersion() {
            return appVersion;
        }

        public void setAppVersion(String appVersion) {
            this.appVersion = appVersion;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getMediumType() {
            return mediumType;
        }

        public void setMediumType(String mediumType) {
            this.mediumType = mediumType;
        }

        public String getSystemVersion() {
            return systemVersion;
        }

        public void setSystemVersion(String systemVersion) {
            this.systemVersion = systemVersion;
        }

        public String getDeviceBrand() {
            return deviceBrand;
        }

        public void setDeviceBrand(String deviceBrand) {
            this.deviceBrand = deviceBrand;
        }

        public String getSystemModel() {
            return systemModel;
        }

        public void setSystemModel(String systemModel) {
            this.systemModel = systemModel;
        }

        public String getSystemLanguage() {
            return systemLanguage;
        }

        public void setSystemLanguage(String systemLanguage) {
            this.systemLanguage = systemLanguage;
        }

        public String getKeep() {
            return keep;
        }

        public void setKeep(String keep) {
            this.keep = keep;
        }

        public String getFlowKeep() {
            return flowKeep;
        }

        public void setFlowKeep(String flowKeep) {
            this.flowKeep = flowKeep;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSignInfo() {
            return signInfo;
        }

        public void setSignInfo(String signInfo) {
            this.signInfo = signInfo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getRandomKey() {
            return randomKey;
        }

        public void setRandomKey(String randomKey) {
            this.randomKey = randomKey;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }
    }
}
