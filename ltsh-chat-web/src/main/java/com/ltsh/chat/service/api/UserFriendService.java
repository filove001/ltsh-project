package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.UserFriend;

import com.ltsh.chat.service.req.friend.UserFriendAddReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.RequestContext;


/**
 * 好友api
 * Created by Random on 2017/10/23.
 */
public interface UserFriendService extends BaseService<UserFriend> {
//    public Result<PageResult<FriendQueryResp>> page(PageReq req);
    public Result add(RequestContext<UserFriendAddReq> req);
}
