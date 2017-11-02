package org.ltsh.chat.service.req;

import lombok.Data;
import org.ltsh.common.entity.ToKenContext;

/**
 * Created by Random on 2017/10/23.
 */
@Data
public class PageReq extends ToKenContext {
    /**
     * 当前页
     */
    private Long pageNumber;
    /**
     * 显示条数
     */
    private Long pageSize;
}
