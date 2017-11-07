package org.ltsh.chat.web.business.user.req;

import lombok.Data;
import org.ltsh.chat.web.common.req.AppContext;

/**
 * Created by Random on 2017/10/12.
 */
@Data
public class LoginVerifyReq extends AppContext {
    private String loginName;
    private String password;
    private String passwordRandomStr;

}
