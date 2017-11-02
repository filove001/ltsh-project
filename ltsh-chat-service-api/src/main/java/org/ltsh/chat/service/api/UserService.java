package org.ltsh.chat.service.api;

import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.service.req.user.LoginQueryServiceReq;
import org.ltsh.chat.service.req.user.LoginVerifyServiceReq;
import org.ltsh.chat.service.req.user.RandomServiceStrGetReq;
import org.ltsh.chat.service.resp.user.RandomStrGetResp;
import org.ltsh.common.entity.UserToken;

/**
 * Created by Random on 2017/10/10.
 */
public interface UserService {
    /**
     * 登录查询
     * @return
     */
    public Result<UserToken> loginQuery(LoginQueryServiceReq req);

    /**
     * 登录
     * @return
     */
    public Result<UserToken> loginVerify(LoginVerifyServiceReq req);
    /**
     * 随机数
     */
    public Result<RandomStrGetResp> getRandomStr(RandomServiceStrGetReq req);
}
