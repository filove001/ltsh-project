package org.ltsh.chat.service.impl;

import org.beetl.sql.core.engine.PageQuery;
import org.ltsh.chat.service.api.UserFriendService;
import org.ltsh.chat.service.dao.UserFriendDao;
import org.ltsh.chat.service.entity.UserFriend;
import org.ltsh.chat.service.req.PageReq;
import org.ltsh.chat.service.resp.FriendQueryResp;
import org.ltsh.chat.service.resp.PageResult;
import org.ltsh.chat.service.resp.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Random on 2017/10/23.
 */
@Service
public class UserFriendServiceImpl implements UserFriendService {
    @Autowired
    private UserFriendDao userFriendDao;
    @Override
    public Result<PageResult<FriendQueryResp>> page(PageReq req) {
        UserFriend userFriend = new UserFriend();
        userFriend.setCreateBy(req.getUserToken().getId());
        BeanUtils.copyProperties(req, userFriend);
        PageQuery<UserFriend> pageQuery = new PageQuery<>();
        pageQuery.setPageNumber(req.getPageNumber());
        pageQuery.setPageSize(req.getPageSize());
        pageQuery.setParas(userFriend);
        userFriendDao.page(pageQuery);

        PageResult<FriendQueryResp> pageResult = new PageResult<>();
        pageResult.setPageNumber(pageQuery.getPageNumber());
        pageResult.setPageSize(pageQuery.getPageSize());
        pageResult.setTotalRow(pageQuery.getTotalRow());
        List<FriendQueryResp> respList = new ArrayList<>();
        for (UserFriend tmp: pageQuery.getList()) {
            FriendQueryResp friendQueryResp = new FriendQueryResp();
            BeanUtils.copyProperties(tmp, friendQueryResp);
            respList.add(friendQueryResp);
        }
        pageResult.setResultList(respList);
//        userFriendDao.getSQLManager().get
        return new Result(pageResult);
    }
}
