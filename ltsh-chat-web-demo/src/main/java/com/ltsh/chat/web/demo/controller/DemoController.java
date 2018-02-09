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

    @RequestMapping({"/", "index.view"})
    public String index(HttpServletRequest request){
        return "/index";
    }
    @RequestMapping("/chat/demo/*.view")
    public String jsp(HttpServletRequest request){
        return request.getRequestURI().substring(0, request.getRequestURI().lastIndexOf("."));
    }


    @ResponseBody
    @RequestMapping(value = "/demo/chat/getIp", method = RequestMethod.GET)
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




}
