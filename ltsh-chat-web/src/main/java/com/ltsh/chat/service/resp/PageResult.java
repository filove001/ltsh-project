package com.ltsh.chat.service.resp;

import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.common.entity.BaseResult;
import lombok.Data;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Random on 2017/10/23.
 */
@Data
public class PageResult<T> extends BaseResult {
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

    public PageResult() {
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }
    public PageResult(String code, String message) {
        super(code, message);
    }
}
