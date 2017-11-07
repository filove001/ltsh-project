package org.ltsh.chat.web.business.user.req;

import lombok.Data;
import org.ltsh.chat.web.common.req.AppContext;

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
