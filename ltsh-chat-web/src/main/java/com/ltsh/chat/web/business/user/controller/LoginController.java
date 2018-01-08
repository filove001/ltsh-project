package com.ltsh.chat.web.business.user.controller;

import com.ltsh.chat.service.api.UserService;
import com.ltsh.chat.service.req.user.LoginVerifyServiceReq;
import com.ltsh.chat.service.req.user.RandomServiceStrGetReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.resp.user.RandomStrGetResp;
import com.ltsh.chat.web.common.controller.BaseController;
import com.ltsh.chat.web.business.user.req.LoginVerifyReq;
import com.ltsh.chat.web.business.user.req.RandomStrGetReq;
import com.ltsh.common.entity.UserToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/9/29.
 */
@Controller
@RequestMapping("/chat/login")
public class LoginController extends BaseController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/loginVerify")
    public Result<UserToken> loginVerify(LoginVerifyReq req){
        LoginVerifyServiceReq serviceReq = new LoginVerifyServiceReq();
        BeanUtils.copyProperties(req, serviceReq);
        serviceReq.setRandomKey(req.getPasswordRandomStr());
        return userService.loginVerify(serviceReq);
//        return null;
    }
    @ResponseBody
    @RequestMapping("/getRandomStr")
    public Result<RandomStrGetResp> getRandomStr(RandomStrGetReq req){
//        return userService.loginVerify(req);
        RandomServiceStrGetReq randomStrGetReq = new RandomServiceStrGetReq();
        BeanUtils.copyProperties(req, randomStrGetReq);
        return userService.getRandomStr(randomStrGetReq);
    }
}
