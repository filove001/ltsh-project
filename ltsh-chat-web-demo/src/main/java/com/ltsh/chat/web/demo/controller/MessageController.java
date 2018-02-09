package com.ltsh.chat.web.demo.controller;

import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.JsonUtils;
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
@RequestMapping("/demo/chat/message")
public class MessageController extends BaseController {
    @ResponseBody
    @RequestMapping("/sendMessage")
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
    @RequestMapping("/getMessage")
    public Map getMessage(HttpServletRequest request) {
        RequestContext requestContext = getBaseParams(request, null);
        String[] random = getRandom(request);
        setSignInfo(requestContext, random);
        String post = httpRequest(baseUrl + "/chat/message/getMessage", requestContext);
        return JsonUtils.fromJson(post, Map.class);
    }
}
