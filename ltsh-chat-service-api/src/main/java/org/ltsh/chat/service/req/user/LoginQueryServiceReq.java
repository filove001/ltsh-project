package org.ltsh.chat.service.req.user;

import lombok.Data;
import org.ltsh.common.entity.ApiContext;

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
