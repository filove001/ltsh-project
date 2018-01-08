package com.ltsh.chat.service.req;

import com.ltsh.common.entity.ToKenContext;
import lombok.Data;

/**
 * Created by Random on 2017/10/23.
 */
@Data
public class PageReq<T> extends ToKenContext<T> {
    /**
     * 当前页
     */
    private Long pageNumber;
    /**
     * 显示条数
     */
    private Long pageSize;
}
