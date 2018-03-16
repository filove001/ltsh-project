package com.ltsh.chat.service.req.user;

import com.ltsh.chat.service.req.WebReq;
import com.ltsh.common.entity.ApiContext;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Random on 2017/11/7.
 */
@Data
public class UserRegisterReq extends WebReq {
    /**
     * 登录名
     */
    @NotEmpty
    private String loginName;
    /**
     * 密码
     */
    @NotEmpty
    private String password;
    /**
     * 昵称
     */
    @NotEmpty
    private String nickName;
}
