package com.ltsh.chat.web.business.message.req;

import com.ltsh.chat.web.common.req.AppContext;
import lombok.Data;


/**
 * Created by Random on 2017/07/26.
 */
@Data
public class MessageReadReq extends AppContext {
    /**
     * 读取的id
     */
    private Integer readId;
    /**
     * 读取类型
     */
    private String readType;


}
