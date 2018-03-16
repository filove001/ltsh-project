package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.req.ServiceReq;
import com.ltsh.chat.service.req.user.LoginVerifyReq;
import com.ltsh.chat.service.req.user.UserRegisterReq;

import com.ltsh.util.beetsql.api.BaseService;
import com.ltsh.util.beetsql.entity.UserToken;
import com.ltsh.util.beetsql.entity.req.BaseReq;
import com.ltsh.util.beetsql.entity.result.ContentResult;

/**
 * Created by Random on 2017/10/10.
 */
public interface UserService extends BaseService<UserInfo> {
    /**
     * 注册
     * @param req
     * @return
     */
    public ContentResult register(BaseReq<UserRegisterReq> req);
    /**
     * 登录查询
     * @return
     */
    public ContentResult<UserToken> loginQuery(ServiceReq req);

    /**
     * 登录
     * @return
     */
    public ContentResult<UserToken> loginVerify(ServiceReq<LoginVerifyReq> req);

}
