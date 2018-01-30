package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.BaseService;
import com.ltsh.chat.service.dao.BaseDao;
import com.ltsh.chat.service.entity.BaseEntity;

import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.req.PageReq;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.RequestContext;

import org.beetl.sql.core.engine.PageQuery;


import java.util.Date;
import java.util.List;

/**
 * Created by fengjianbo on 2017/12/28.
 */
public abstract class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {
    protected BaseDao baseDao;
    @Override
    public Result insert(RequestContext<T> req) {
        T content = req.getContent();
        content.setCreateTime(new Date());
        content.setCreateBy(req.getUserToken().getId());
        Result result = repetitionVerify(req.getContent());
        if(result.isSucceed()) {
            baseDao.insert(content);
        } else {
            return result;
        }

        return new Result(ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result update(RequestContext<T> req) {
        T content = req.getContent();
        content.setUpdateTime(new Date());
        content.setUpdateBy(req.getUserToken().getId());
        baseDao.updateById(content);
        return new Result(ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result delete(RequestContext<T> req) {
        baseDao.deleteById(req.getContent().getId());
        return new Result(ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result<T> getById(RequestContext<T> req) {
        Object unique = baseDao.unique(req.getContent().getId());
        return new Result<>((T)unique);
    }

    @Override
    public Result<List<T>> getByTemplate(RequestContext<T> req) {
        List template = baseDao.template(req.getContent());
        return new Result<>(template);
    }

    /**
     * 分页查询
     * @param req
     * @return
     */
    public PageResult<T> page(PageReq<T> req) {
        PageQuery pageQuery = new PageQuery<>();
        pageQuery.setPageNumber(req.getPageNumber());
        pageQuery.setPageSize(req.getPageSize());
        pageQuery.setParas(req.getContent());
        baseDao.page(pageQuery);
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageNumber(pageQuery.getPageNumber());
        pageResult.setPageSize(pageQuery.getPageSize());
        pageResult.setTotalRow(pageQuery.getTotalRow());
        pageResult.setResultList(pageQuery.getList());
        return pageResult;
    }

    public Result repetitionVerify(T content) {
        return new Result(ResultCodeEnum.SUCCESS);
    }
}
