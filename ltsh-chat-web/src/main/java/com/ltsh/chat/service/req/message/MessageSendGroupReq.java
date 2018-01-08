package com.ltsh.chat.service.req.message;

import com.ltsh.chat.service.entity.MessageInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by fengjianbo on 2017/12/28.
 */
@Data
public class MessageSendGroupReq extends MessageInfo {
    /**
     * 群组id
     */
    private Integer groupId;

}
