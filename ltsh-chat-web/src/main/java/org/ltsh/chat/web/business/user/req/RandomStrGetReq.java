package org.ltsh.chat.web.business.user.req;

import lombok.Data;
import org.ltsh.chat.web.common.req.AppContext;

/**
 * Created by Random on 2017/10/11.
 */
@Data
public class RandomStrGetReq extends AppContext {
    private String uuid;
}
