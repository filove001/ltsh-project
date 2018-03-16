package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.UserGroupRel;
import com.ltsh.util.beetsql.dao.BaseDao;
import org.beetl.sql.core.engine.PageQuery;

/**
 * Created by fengjianbo on 2018-01-03 17:36:59.
 */
public interface UserGroupRelDao extends BaseDao<UserGroupRel> {
    /**
     * 查询好友,分页
     * @param pageQuery
     */
    public void page(PageQuery<UserGroupRel> pageQuery);
}
