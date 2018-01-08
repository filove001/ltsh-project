package com.ltsh.chat.service.dao;

import com.ltsh.chat.service.entity.MessageInfoFile;
import org.beetl.sql.core.engine.PageQuery;
/**
 * Created by fengjianbo on 2018-01-05 11:50:30.
 */
public interface MessageInfoFileDao extends BaseDao<MessageInfoFile> {
    /**
     * 查询,分页
     * @param pageQuery
     */
    public void page(PageQuery<MessageInfoFile> pageQuery);
}
