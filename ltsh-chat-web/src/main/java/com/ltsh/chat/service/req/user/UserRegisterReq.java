package com.ltsh.chat.service.req.user;

import com.ltsh.common.entity.ApiContext;
import com.ltsh.common.entity.BaseReq;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Random on 2017/11/7.
 */
@Data
public class UserRegisterReq extends BaseReq {
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
