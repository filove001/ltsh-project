package com.ltsh.chat.web.controller.user;

import com.ltsh.chat.service.api.UserFriendService;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.req.friend.UserFriendAddReq;
import com.ltsh.chat.service.resp.user.FriendQueryResp;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;

import com.ltsh.chat.web.common.annotation.CheckLogin;
import com.ltsh.chat.web.common.controller.BaseController;
import com.ltsh.chat.web.common.req.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/10/24.
 */
@Controller
@RequestMapping("/chat/userFriend")
public class UserFriendController extends BaseController {
    @Autowired
    private UserFriendService userFriendService;

    @ResponseBody
    @RequestMapping("/page")
    @CheckLogin
    public PageResult<UserFriend> page(@RequestBody AppContext<PageReq<UserFriend>> req){
        return userFriendService.page(req);
    }
    @ResponseBody
    @RequestMapping("/add")
    @CheckLogin
    public Result add(@RequestBody AppContext<UserFriendAddReq> req){
        return userFriendService.add(req);
    }
}
