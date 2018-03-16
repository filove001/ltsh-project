package com.ltsh.util.beetsql.impl;







import com.ltsh.common.util.enums.ResultCode;
import com.ltsh.util.beetsql.api.BaseService;
import com.ltsh.util.beetsql.dao.BaseDao;
import com.ltsh.util.beetsql.entity.BaseEntity;

import com.ltsh.util.beetsql.entity.req.BaseReq;
import com.ltsh.util.beetsql.entity.req.PageReq;
import com.ltsh.util.beetsql.entity.result.ContentResult;
import com.ltsh.util.beetsql.entity.result.PageResult;
import org.beetl.sql.core.engine.PageQuery;

import java.util.Date;
import java.util.List;

/**
 * Created by fengjianbo on 2017/12/28.
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected BaseDao<T> baseDao;
    @Override
    public void insert(T t) {
        t.setCreateTime(new Date());
        baseDao.insert(t, true);
    }

    @Override
    public int updateById(T t) {
        t.setUpdateTime(new Date());
        return baseDao.updateById(t);
    }

    @Override
    public int deleteById(Integer id) {
        return baseDao.deleteById(id);
    }

    @Override
    public T unique(Integer id) {
        return (T)baseDao.unique(id);
    }

    @Override
    public List<T> template(T t) {
        return (List<T>)baseDao.template(t);
    }

    @Override
    public T templateOne(T t) {
        return (T)baseDao.templateOne(t);
    }


    public PageResult<T> templatePage(Long pageNumber, Long pageSize,T t) {
        PageQuery<T> pageQuery = new PageQuery<T>(pageNumber, pageSize, t);
        baseDao.page(pageQuery);
        PageResult<T> pageResult = new PageResult<T>(pageNumber, pageSize, pageQuery.getTotalRow(), pageQuery.getList());
        return pageResult;
    }


    public ContentResult repetitionVerify(T content) {
        return new ContentResult(ResultCode.CG);
    }
}
