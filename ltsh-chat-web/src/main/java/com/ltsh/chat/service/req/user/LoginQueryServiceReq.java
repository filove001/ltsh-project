package com.ltsh.chat.service.req.user;

import com.ltsh.common.entity.ApiContext;
import lombok.Data;

/**
 * 登录查询请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class LoginQueryServiceReq extends ApiContext {
    /**
     * token
     */
    private String token;
    /**
     * 设备id
     */
    private String medium;

}
