package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.util.beetsql.dao.BaseDao;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

/**
 * Created by Random on 2017/10/9.
 */
public interface UserInfoDao extends BaseDao<UserInfo> {
    /**
     * 查询好友,分页
     * @param pageQuery
     */
    public void page(PageQuery<UserInfo> pageQuery);
}
