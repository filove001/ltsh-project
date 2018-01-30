package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.req.user.LoginVerifyReq;
import com.ltsh.chat.service.req.user.UserRegisterReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.RequestContext;
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
    public Result register(RequestContext<UserRegisterReq> req);
    /**
     * 登录查询
     * @return
     */
    public Result<UserToken> loginQuery(RequestContext req);

    /**
     * 登录
     * @return
     */
    public Result<UserToken> loginVerify(RequestContext<LoginVerifyReq> req);

}
