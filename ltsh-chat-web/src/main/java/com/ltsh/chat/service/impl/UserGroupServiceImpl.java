package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserGroupService;

import com.ltsh.chat.service.dao.UserGroupDao;
import com.ltsh.chat.service.entity.UserGroup;

import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.common.entity.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fengjianbo on 2018-01-03 17:36:59.
 */
@Service
public class UserGroupServiceImpl extends BaseServiceImpl<UserGroup> implements UserGroupService {

    private UserGroupDao userGroupDao;
    @Autowired
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
        this.baseDao = userGroupDao;
    }

    @Override
    public PageResult<UserGroup> page(RequestContext<PageReq<UserGroup>> req) {
        return super.page(req);
    }
}
