package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.req.user.LoginQueryServiceReq;
import com.ltsh.chat.service.req.user.LoginVerifyServiceReq;
import com.ltsh.chat.service.req.user.RandomServiceStrGetReq;
import com.ltsh.chat.service.req.user.UserRegisterServiceReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.resp.user.RandomStrGetResp;
import com.ltsh.common.entity.UserToken;

/**
 * Created by Random on 2017/10/10.
 */
public interface UserService extends BaseService<UserInfo> {
    /**
     * 注册
     * @param req
     * @return
     */
    public Result register(UserRegisterServiceReq req);
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
