package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserFriendService;

import com.ltsh.chat.service.dao.UserFriendDao;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Random on 2017/10/23.
 */
@Service
public class UserFriendServiceImpl extends BaseServiceImpl<UserFriend> implements UserFriendService {
    @Autowired
    private UserFriendDao userFriendDao;

    public void setUserFriendDao(UserFriendDao userFriendDao) {
        this.userFriendDao = userFriendDao;
        this.baseDao = userFriendDao;
    }

    @Override
    public Result<PageResult<UserFriend>> page(PageReq<UserFriend> req) {
        if(req.getContent() == null) {
            req.setContent(new UserFriend());
        }
        req.getContent().setCreateBy(req.getUserToken().getId());

        return super.page(req);
    }

    @Override
    public Result repetitionVerify(UserFriend content) {
//        userInfo.setLoginName(req.getLoginName());
//        userInfo = userInfoDao.getSQLManager().templateOne(userInfo);
//        if(userInfo != null) {
        return super.repetitionVerify(content);
    }
}
