package org.ltsh.chat.service.api;

import org.ltsh.chat.service.resp.MessageGetServiceResp;
import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.service.req.message.MessageGetServiceReq;
import org.ltsh.chat.service.req.message.MessageSendServiceReq;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
public interface MessageService {
    /**
     * 发送消息
     * @param req
     */
    public Result sendMsg(MessageSendServiceReq req);

    /**
     * 获取消息
     * @param req
     */
    public Result<MessageGetServiceResp> getMsg(MessageGetServiceReq req);

}
