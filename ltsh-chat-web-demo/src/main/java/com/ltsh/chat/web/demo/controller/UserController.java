package com.ltsh.chat.web.demo.controller;

import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.security.MD5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengjianbo on 2018/2/5.
 */
@Controller
@RequestMapping("/demo/chat/user")
public class UserController extends BaseController {
    @ResponseBody
    @RequestMapping("/loginVerify")
    public Map loginVerify(HttpServletRequest request) {
        Map<String, Object> baseParams = new HashMap<>();
        String[] random = getRandom(request);
        String[] passwordRandom = getRandom(request);
        baseParams.put("loginName", request.getParameter("loginName"));
        String password = request.getParameter("password");
        baseParams.put("password", getPassword(password, passwordRandom[1]));
        baseParams.put("passwordRandomKey", passwordRandom[0]);
        RequestContext<Map<String, Object>> requestContext = getBaseParams(request, baseParams);
        setSignInfo(requestContext, random);
        String post = httpRequest(baseUrl + "/chat/user/loginVerify", requestContext);
        return JsonUtils.fromJson(post, Map.class);
    }

    @ResponseBody
    @RequestMapping("/register")
    public Map register(HttpServletRequest request) {
        Map<String, Object> baseParams = new HashMap<>();
        String[] random = getRandom(request);
        baseParams.put("loginName", request.getParameter("loginName"));
        baseParams.put("nickName", request.getParameter("nickName"));

        String password = createPassword(request.getParameter("password"));
        baseParams.put("password", password);
        RequestContext<Map<String, Object>> requestContext = getBaseParams(request, baseParams);
        setSignInfo(requestContext, random);
        String post = httpRequest(baseUrl + "/chat/user/register", requestContext);
        return JsonUtils.fromJson(post, Map.class);
    }
    private String createPassword(String password) {
        return MD5Util.encoder("ltshUser:" + password);
    }
    private String getPassword(String password, String randomValue) {
        return MD5Util.encoder("ltshChat:" + MD5Util.encoder("chat:"+MD5Util.encoder("ltshUser:" + password.toString())) + randomValue);
    }
}
