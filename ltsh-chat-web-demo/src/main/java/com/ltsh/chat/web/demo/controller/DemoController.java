package com.ltsh.chat.web.demo.controller;

import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.http.OkHttpUtils;
import com.ltsh.common.util.security.AES;
import com.ltsh.common.util.security.MD5Util;
import com.ltsh.common.util.security.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengjianbo on 2018/1/8.
 */
@Controller
public class DemoController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Value("${chat.service.url}")
    private String baseUrl;
    @RequestMapping({"/", "index.html"})
    public String index(HttpServletRequest request){
        return "/index";
    }
    @RequestMapping("/chat/demo/*.html")
    public String jsp(HttpServletRequest request){
        return request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf("."));
    }
    @ResponseBody
    @RequestMapping("/chat/demo/sendMessage")
    public Map sendMessage(HttpServletRequest request) {
        Map<String, Object> baseParams = new HashMap<>();
        baseParams.put("msgContext", request.getParameter("msgContext"));
        baseParams.put("msgType", 0);
        baseParams.put("sendType", 0);
        baseParams.put("toUser", Integer.parseInt(request.getParameter("toUser")));
        String[] random = getRandom(request);
        RequestContext<Map<String, Object>> requestContext = getBaseParams(request, baseParams);
        setSignInfo(requestContext, random);
        String post = httpRequest(baseUrl + "/chat/message/sendMessage", requestContext);
        return JsonUtils.fromJson(post, Map.class);
    }
    @ResponseBody
    @RequestMapping("/chat/demo/getMessage")
    public Map getMessage(HttpServletRequest request) {
        RequestContext requestContext = getBaseParams(request, null);
        String[] random = getRandom(request);
        setSignInfo(requestContext, random);
        String post = httpRequest(baseUrl + "/chat/message/getMessage", requestContext);
        return JsonUtils.fromJson(post, Map.class);
    }
    private String getPassword(String password, String randomValue) {
        return MD5Util.encoder("ltshChat:" + MD5Util.encoder("chat:"+MD5Util.encoder("ltshUser:" + password.toString())) + randomValue);
    }
    @ResponseBody
    @RequestMapping("/chat/demo/loginVerify")
    public Map loginVerify(HttpServletRequest request) {
        Map<String, Object> baseParams = new HashMap<>();
        String[] random = getRandom(request);
        String[] passwordRandom = getRandom(request);
        baseParams.put("loginName", request.getParameter("loginName"));
        String password = request.getParameter("password");
        baseParams.put("password", getPassword(password, passwordRandom[1]));
        baseParams.put("passwordRandomStr", passwordRandom[0]);
        RequestContext<Map<String, Object>> requestContext = getBaseParams(request, baseParams);
        setSignInfo(requestContext, random);
        String post = httpRequest(baseUrl + "/chat/user/loginVerify", requestContext);
        return JsonUtils.fromJson(post, Map.class);
    }
    @ResponseBody
    @RequestMapping(value = "/chat/demo/getIp", method = RequestMethod.GET)
    public Map getIp(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");
        Map<String, Object> map = new HashMap<>();
        map.put("remoteAddr", remoteAddr);
        map.put("forwarded", forwarded);
        map.put("realIp", realIp);
        logger.info("ip:{}", JsonUtils.toJson(map));
        return map;
    }

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
//        LogUtils.info("签名明文:{}", signStr);
        String signInfo = null;
        if(random != null) {
//            params.put("randomKey", random[0]);
            params.setRandomKey(random[0]);
            signStr = SignUtils.getSignStr(params);
            signInfo = SignUtils.getSign(signStr, "123456", random[1]);
        } else {
            signInfo = SignUtils.getSign(signStr, "123456", "");
        }

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
