package com.ltsh.chat.web.demo.controller;

import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.http.OkHttpUtils;
import com.ltsh.common.util.security.SignUtils;
import sun.misc.Request;

import java.util.Map;

/**
 * Created by fengjianbo on 2018/1/8.
 */
public class BaseController {
    protected String httpRequest(String url, RequestContext requestContext) {
        Map json = JsonUtils.fromJson(JsonUtils.toJson(requestContext), Map.class);
        return OkHttpUtils.postJson(url, json);
    }
}
