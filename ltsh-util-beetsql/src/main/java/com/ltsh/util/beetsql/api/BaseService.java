package com.ltsh.util.beetsql.api;


import com.ltsh.util.beetsql.entity.BaseEntity;
import com.ltsh.util.beetsql.entity.result.PageResult;

import java.util.List;


/**
 * Created by fengjianbo on 2017/12/28.
 */
public interface BaseService<T extends BaseEntity> {
    /**
     * 添加
     * @param t
     */
    public void insert(T t);

    /**
     * 修改
     * @param t
     */
    public int updateById(T t);
    /**
     * 删除
     * @param id
     */
    public int deleteById(Integer id);
    /**
     * 按照id获取
     * @param id
     */
    public T unique(Integer id);

    /**
     * 按照模板获取
     * @param t
     * @return
     */
    public List<T> template(T t);
    /**
     * 按照模板获取
     * @param t
     * @return
     */
    public T templateOne(T t);
    /**
     * 分页查询
     * @param t
     * @return
     */
    public PageResult<T> templatePage(Long pageNumber, Long pageSize,T t);
}
