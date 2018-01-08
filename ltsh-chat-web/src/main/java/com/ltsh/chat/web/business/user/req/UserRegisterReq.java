package com.ltsh.chat.web.business.user.req;

import com.ltsh.chat.web.common.req.AppContext;
import lombok.Data;

/**
 * Created by Random on 2017/11/7.
 */
@Data
public class UserRegisterReq extends AppContext {
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
