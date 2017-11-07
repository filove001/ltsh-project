package org.ltsh.chat.service.req.user;

import lombok.Data;
import org.ltsh.common.entity.ApiContext;

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
