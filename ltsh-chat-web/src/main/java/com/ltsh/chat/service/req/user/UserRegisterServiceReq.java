package com.ltsh.chat.service.req.user;

import com.ltsh.common.entity.ApiContext;
import lombok.Data;

/**
 * Created by Random on 2017/11/7.
 */
@Data
public class UserRegisterServiceReq extends ApiContext {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
}
