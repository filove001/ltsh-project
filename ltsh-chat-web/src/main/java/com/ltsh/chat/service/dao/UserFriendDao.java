package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.UserFriend;
import com.ltsh.util.beetsql.dao.BaseDao;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by Random on 2017/10/23.
 */
public interface UserFriendDao extends BaseDao<UserFriend> {
    /**
     * 查询好友,分页
     * @param pageQuery
     */
    public void page(PageQuery<UserFriend> pageQuery);
}
