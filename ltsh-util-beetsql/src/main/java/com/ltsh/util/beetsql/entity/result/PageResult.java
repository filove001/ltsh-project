package com.ltsh.util.beetsql.entity.result;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fengjianbo on 2018/3/8.
 */
public class PageResult<T> implements Serializable {
    /**
     * 当前页
     */
    private Long pageNumber;
    /**
     * 显示条数
     */
    private Long pageSize;
    /**
     * 总数据行
     */
    private Long totalRow;
    /**
     * 总页数
     */
    private Long totalPage;


    /**
     * 结果集
     */
    private List<T> resultList;


    public PageResult(Long pageNumber, Long pageSize, Long totalRow, List<T> resultList) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalRow = totalRow;
        this.resultList = resultList;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }
    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Long totalRow) {
        this.totalRow = totalRow;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}
