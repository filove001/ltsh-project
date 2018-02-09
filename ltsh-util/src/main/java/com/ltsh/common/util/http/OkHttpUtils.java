package com.ltsh.common.util.http;



import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;



import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Random on 2017/09/06.
 */
public class OkHttpUtils {

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/octet-stream; charset=utf-8");
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    static OkHttpClient client = new OkHttpClient.Builder().readTimeout(70L, TimeUnit.SECONDS).writeTimeout(70L, TimeUnit.SECONDS).connectTimeout(70L, TimeUnit.SECONDS).build();

    public static String postForm(String url, Map<String, Object> json) {
        return postForm(url, json, null);
    }

    public static String postJson(String url, Map<String, Object> json) {
        RequestBody requestBody = RequestBody.create(JSON, JsonUtils.toJson(json));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return execute(request);
    }
    private static String execute(Request request) {
        Response response = null;
        try {
            response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        } catch (Exception e) {
            LogUtils.error(e.getMessage(), e);
        } finally {
            if(response != null) {
                response.close();
            }
        }
        Map<String, Object> res = new HashMap<>();
        res.put("code", "999999");
        res.put("message", "链接超时");
        return JsonUtils.toJson(res);
    }
    public static String postForm(String url, Map<String, Object> json, Map<String, List<File>> fileMap) {
        return httpRequest(url, "POST", json, fileMap);
    }

    public static String get(String url) {
        return httpRequest(url, "GET", null, null);
    }

    public static String httpRequest(String url, String methodType, Map<String, Object> json, Map<String, List<File>> fileMap) {

        Request request = null;
        if(methodType.equals("POST")) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            for (String key: json.keySet()) {
                if(json.get(key) != null) {
                    builder.addFormDataPart(key, String.valueOf(json.get(key)));
                }
            }
            if(fileMap != null) {
                for (String key : fileMap.keySet()) {
                    List<File> files = fileMap.get(key);
                    if (files != null) {
                        for (File file : files) {
                            builder.addFormDataPart("file", file.getName(),
                                    RequestBody.create(MEDIA_TYPE_MARKDOWN, file));
                        }
                    }
                }
            }
            RequestBody requestBody = builder.build();
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
        }
        return execute(request);
    }
}
