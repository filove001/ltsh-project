package com.ltsh.chat.web.business.user.req;

import com.ltsh.chat.web.common.req.AppContext;
import lombok.Data;

/**
 * Created by Random on 2017/10/12.
 */
@Data
public class LoginVerifyReq extends AppContext {
    private String loginName;
    private String password;
    private String passwordRandomStr;

}
