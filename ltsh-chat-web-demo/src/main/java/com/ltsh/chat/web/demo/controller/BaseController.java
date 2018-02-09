package com.ltsh.chat.web.demo.controller;

import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.http.OkHttpUtils;
import com.ltsh.common.util.security.AES;
import com.ltsh.common.util.security.SignUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by fengjianbo on 2018/1/8.
 */
@Controller
public class BaseController {
    protected String httpRequest(String url, RequestContext requestContext) {
        Map json = JsonUtils.fromJson(JsonUtils.toJson(requestContext), Map.class);
        return OkHttpUtils.postJson(url, json);
    }
    @Value("${chat.service.url}")
    protected String baseUrl;
    public String[] getRandom(HttpServletRequest request) {
        String uuid = StringUtils.getUUID();
        RequestContext<String> requestContext = getBaseParams(request, uuid);
        setSignInfo(requestContext, null);
        String post = httpRequest(baseUrl + "/chat/basics/getRandomStr", requestContext);
        Map map = JsonUtils.fromJson(post, Map.class);
        Map content = (Map)map.get("content");
        String randomKey = (String)content.get("randomKey");
        String randomValue = (String)content.get("randomValue");
        randomKey = AES.decrypt(randomKey, uuid);
        randomValue = AES.decrypt(randomValue, uuid);
        String[] strs = new String[]{randomKey, randomValue};
        return strs;
    }
    public void setSignInfo(RequestContext params, String[] random) {
        String signStr = SignUtils.getSignStr(params);
        String signInfo = null;
        String randomValue = "";
        if(random != null) {
//            params.put("randomKey", random[0]);
            params.setRandomKey(random[0]);
            signStr = SignUtils.getSignStr(params);
            randomValue = random[1];
        } else {

        }
        LogUtils.info("签名明文:{}", signStr);
        signInfo = SignUtils.getSign(signStr, "123456", randomValue);
        params.setSignInfo(signInfo);
    }
    public <T> RequestContext<T> getBaseParams(HttpServletRequest request, T obj) {
        RequestContext<T> requestContext = new RequestContext<T>();
        requestContext.setContent(obj);
        requestContext.setAppId("app");
        requestContext.setKeep(StringUtils.getUUID());
        requestContext.setAppVersion("1.0");
        requestContext.setTimestamp(Calendar.getInstance().getTimeInMillis() + "");
        requestContext.setMedium("0000000000");
        requestContext.setMediumType("1");
        requestContext.setToken(request.getParameter("token"));

        return requestContext;
    }
}
