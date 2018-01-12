package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.api.BaseService;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.business.user.req.UserFriendAddReq;
import com.ltsh.common.entity.ToKenContext;

/**
 * 好友api
 * Created by Random on 2017/10/23.
 */
public interface UserFriendService extends BaseService<UserFriend> {
//    public Result<PageResult<FriendQueryResp>> page(PageReq req);
    public Result add(ToKenContext<UserFriendAddReq> req);
}
