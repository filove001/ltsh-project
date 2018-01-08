package com.ltsh.chat.service.req.user;

import com.ltsh.common.entity.ApiContext;
import lombok.Data;

/**
 * 登录校验请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class LoginVerifyServiceReq extends ApiContext {
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 随机数key
     */
    private String randomKey;
    /**
     * 设备id
     */
    private String medium;
}
