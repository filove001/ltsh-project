package org.ltsh.chat.web.business.user.controller;

import org.ltsh.chat.service.api.UserFriendService;
import org.ltsh.chat.service.req.PageReq;
import org.ltsh.chat.service.resp.FriendQueryResp;
import org.ltsh.chat.service.resp.PageResult;
import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.web.common.annotation.CheckLogin;
import org.ltsh.chat.web.common.controller.BaseController;
import org.ltsh.chat.web.common.req.PageContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/10/24.
 */
@Controller
@RequestMapping("/chat/friend")
public class UserFriendController extends BaseController {
    @Autowired
    private UserFriendService userFriendService;
    @ResponseBody
    @RequestMapping("/page")
    @CheckLogin
    public Result<PageResult<FriendQueryResp>> page(PageContext req){
        PageReq pageReq = new PageReq();
        BeanUtils.copyProperties(req, pageReq);
        return userFriendService.page(pageReq);
    }
}
