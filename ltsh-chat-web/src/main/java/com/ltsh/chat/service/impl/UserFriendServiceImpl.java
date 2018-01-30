package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserFriendService;

import com.ltsh.chat.service.dao.UserFriendDao;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.req.friend.UserFriendAddReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Created by Random on 2017/10/23.
 */
@Service
public class UserFriendServiceImpl extends BaseServiceImpl<UserFriend> implements UserFriendService {

    private UserFriendDao userFriendDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    public void setUserFriendDao(UserFriendDao userFriendDao) {
        this.userFriendDao = userFriendDao;
        this.baseDao = userFriendDao;
    }

    @Override
    public PageResult<UserFriend> page(PageReq<UserFriend> req) {
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

    public Result add(RequestContext<UserFriendAddReq> req) {
        UserFriendAddReq content = req.getContent();
        UserInfo userInfo = new UserInfo();
        userInfo.setLoginName(content.getLoginName());
        UserInfo userInfo1 = userInfoDao.templateOne(userInfo);
        UserFriend userFriend = new UserFriend();
        userFriend.setFriendUserId(userInfo1.getId());
        userFriend.setName(content.getName());
        userFriend.setStatus(StatusEnums.KY.getValue());
        userFriend.setCreateTime(new Date());
        userFriend.setCreateBy(req.getUserToken().getId());
        userFriendDao.insert(userFriend);
        return new Result(ResultCodeEnum.SUCCESS);
    }
}
