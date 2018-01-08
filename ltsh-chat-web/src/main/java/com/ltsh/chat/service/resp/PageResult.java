package com.ltsh.chat.service.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Random on 2017/10/23.
 */
@Data
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
     * 结果集
     */
    private List<T> resultList;
}
