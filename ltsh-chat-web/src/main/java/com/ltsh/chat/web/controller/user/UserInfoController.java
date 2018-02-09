package com.ltsh.chat.web.controller.user;

import com.ltsh.chat.service.api.UserService;
import com.ltsh.chat.service.req.user.LoginVerifyReq;
import com.ltsh.chat.service.req.user.UserRegisterReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.common.controller.BaseController;
import com.ltsh.chat.web.common.req.AppContext;
import com.ltsh.common.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/11/7.
 */
@Controller
@RequestMapping("/chat/user")
public class UserInfoController extends BaseController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/register")
    public Result register(@RequestBody AppContext<UserRegisterReq> req){
        return userService.register(req);
    }
    @ResponseBody
    @RequestMapping("/loginVerify")
    public Result<UserToken> loginVerify(@RequestBody AppContext<LoginVerifyReq> req){
        return userService.loginVerify(req);
//        return null;
    }
    @ResponseBody
    @RequestMapping("/getInfo")
    public Result getInfo(@RequestBody AppContext req){
        Result<UserToken> userTokenResult = userService.loginQuery(req);
        UserToken content = userTokenResult.getContent();
        content.setToken("***");
        return userTokenResult;
    }

}
