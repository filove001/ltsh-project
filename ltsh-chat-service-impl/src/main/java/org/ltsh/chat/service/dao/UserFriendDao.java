package org.ltsh.chat.service.dao;

import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.ltsh.chat.service.entity.UserFriend;

/**
 * Created by Random on 2017/10/23.
 */
public interface UserFriendDao extends BaseMapper<UserFriend> {
    public void page(PageQuery<UserFriend> pageQuery);
}
