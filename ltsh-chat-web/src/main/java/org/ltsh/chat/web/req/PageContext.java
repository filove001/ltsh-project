package org.ltsh.chat.web.req;

import lombok.Data;

/**
 * Created by Random on 2017/10/24.
 */
@Data
public class PageContext extends AppContext {
    private Long pageNumber;
    private Long pageSize;
}
