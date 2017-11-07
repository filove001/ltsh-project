package org.ltsh.common.util;





import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Random on 2017/4/19.
 */
public class JsonUtil {
//    private static Gson gson = new GsonBuilder()
//                    .setLenient()// json宽松
//                    .enableComplexMapKeySerialization()//支持Map的key为复杂对象的形式
//                    .serializeNulls() //智能null
////                    .setPrettyPrinting()// 调教格式
//                    .disableHtmlEscaping() //默认是GSON把HTML 转义的
//                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                    .create();
    /**
     * 将对象转换为Json字符串
     * @param jsonString 需要转换的json字符串
     * @param classOfT 转换的类型
     * @return
     */
    public static <T> T fromJson(String jsonString, Class<T> classOfT) {
        return JSONObject.parseObject(jsonString, classOfT);
//        return gson.fromJson(jsonString, classOfT);
    }
    public static <T> T fromJson(String jsonString, Type classOfT) {
        return JSONObject.parseObject(jsonString, classOfT);
    }

    /**
     * 将对象转换为Json字符串
     * @param object 需要转换的对象
     * @return
     */
    public static String toJson(Object object) {
        return JSONObject.toJSONString(object);
//        return gson.toJson(object);
    }
    /**
     * 将对象转换为Json字符串
     * @param object 需要转换的对象
     * @param encryptions 需要屏蔽显示的值
     * @return
     */
    public static String toJsonLogStr(Object object, String [] encryptions) {
        Object[] objects = new Object[]{object};
        return toJsonLogStr(objects, encryptions);
    }
    public static String toJsonLogStr(List object, String [] encryptions) {
        return toJsonLogStr(object.toArray(), encryptions);
    }
    /**
     * 将对象转换为Json字符串
     * @param objects 需要转换的对象
     * @param encryptions 需要屏蔽显示的值
     * @return
     */
    public static String toJsonLogStr(Object[] objects, String [] encryptions) {
        String replaceStr = "******";
        List list =JsonUtil.fromJson(JsonUtil.toJson(objects), List.class);
//        List list = JsonUtil.fromJson(JsonUtil.toJson(objects), List.class);
        for (int i = 0; i < list.size(); i++) {
            if(encryptions != null) {
                Object object = list.get(i);
                if(object instanceof Map) {
                    Map map = (Map) object;
                    for (String str : encryptions) {
                        if(map.get(str) != null) {
                            map.put(str, replaceStr);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            if(object instanceof Map) {
                Map map = (Map) object;
                recursive(map, 0);
            } else if(object != null && object.toString().length() > 1000) {
                list.set(i, replaceStr);
            }
        }
        return JsonUtil.toJson(list);
    }
    public static void recursive(Map map, int level){
        for (Object obj : map.keySet()) {
            if(level >= 5) {
                map.put(obj, "***");
            }else if(map.get(obj) instanceof Map) {
                recursive((Map)map.get(obj), level + 1);
            } else if(map.get(obj) instanceof List){
                recursive((List)map.get(obj), level + 1);
            }else if(map.get(obj) instanceof String){
                if(map.get(obj) != null && map.get(obj).toString().length() > 1000) {
                    map.put(obj, "***");
                }
            } else {
                if(map.get(obj) != null && map.get(obj).toString().length() > 1000) {
                    map.put(obj, "***");
                }
            }
        }
    }

    public static void recursive(List list, int level){
        for (Object obj : list) {
            if(level >= 5) {
                int i = list.lastIndexOf(obj);
                list.set(i, "***");
            }else if(obj instanceof Map) {
                recursive((Map)obj, level + 1);
            } else if(obj instanceof List){
                recursive((List)obj, level + 1);
            }else if(obj instanceof String){
                if(obj != null && obj.toString().length() > 1000) {
                    int i = list.lastIndexOf(obj);
                    list.set(i, "***");
                }
            }
        }
    }
    public static String[] getEncryption(String ... encryption) {
        String[] strings = {"listThumImg", "token", "encryptCertSerial", "signCertSerial", "signCert", "publicKey", "password", "attachment"};
        if(encryption != null) {
            String[] totalArray = new String[encryption.length + strings.length];
            System.arraycopy(strings, 0, totalArray, 0, strings.length);
            System.arraycopy(encryption, 0, totalArray, strings.length, encryption.length);
            return totalArray;
        }
        return strings;
    }
}


