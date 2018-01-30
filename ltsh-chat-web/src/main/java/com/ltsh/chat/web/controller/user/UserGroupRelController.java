package com.ltsh.chat.web.controller.user;

import com.ltsh.chat.service.api.UserGroupRelService;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.user.FriendQueryResp;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.common.annotation.CheckLogin;
import com.ltsh.chat.web.common.controller.BaseController;
import com.ltsh.chat.web.common.req.PageContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/10/24.
 */
@Controller
@RequestMapping("/chat/userGroupRel")
public class UserGroupRelController extends BaseController {
    @Autowired
    private UserGroupRelService userGroupRelService;
    @ResponseBody
    @RequestMapping("/page")
    @CheckLogin
    public PageResult<FriendQueryResp> page(PageContext req){
        PageReq pageReq = new PageReq();
        BeanUtils.copyProperties(req, pageReq);
        return userGroupRelService.page(pageReq);
    }

}
