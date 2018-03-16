package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.UserFriend;

import com.ltsh.chat.service.req.friend.UserFriendAddReq;
import com.ltsh.util.beetsql.api.BaseService;
import com.ltsh.util.beetsql.entity.req.BaseReq;
import com.ltsh.util.beetsql.entity.result.ContentResult;


/**
 * 好友api
 * Created by Random on 2017/10/23.
 */
public interface UserFriendService extends BaseService<UserFriend> {
//    public Result<PageResult<FriendQueryResp>> page(PageReq req);
    public ContentResult add(BaseReq<UserFriendAddReq> req);
}
