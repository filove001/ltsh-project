package com.ltsh.common.entity;

import lombok.Data;

/**
 * Created by Random on 2017/10/24.
 */
@Data
public class PageContext extends RequestContext {
    private Long pageNumber;
    private Long pageSize;
}
