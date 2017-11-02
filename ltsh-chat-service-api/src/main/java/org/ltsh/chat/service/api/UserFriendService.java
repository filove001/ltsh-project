package org.ltsh.chat.service.api;

import org.ltsh.chat.service.req.PageReq;
import org.ltsh.chat.service.resp.FriendQueryResp;
import org.ltsh.chat.service.resp.PageResult;
import org.ltsh.chat.service.resp.Result;

import java.util.List;

/**
 * 好友api
 * Created by Random on 2017/10/23.
 */
public interface UserFriendService {
    public Result<PageResult<FriendQueryResp>> page(PageReq req);

}
