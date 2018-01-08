package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.UserGroup;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by fengjianbo on 2018-01-03 17:36:59.
 */
public interface UserGroupDao extends BaseDao<UserGroup> {
    /**
     * 查询好友,分页
     * @param pageQuery
     */
    public void page(PageQuery<UserGroup> pageQuery);
}
