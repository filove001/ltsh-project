package com.ltsh.chat.service.req.user;

import com.ltsh.chat.service.req.WebReq;
import com.ltsh.common.entity.ApiContext;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 登录校验请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class LoginVerifyReq extends WebReq {
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
     * 密码随机数
     */
    @NotEmpty
    private String passwordRandomKey;
}
