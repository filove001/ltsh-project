package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.req.message.SendFileMessageReq;
import com.ltsh.chat.service.req.message.SendGroupMessageReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.RequestContext;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
public interface MessageService extends BaseService<MessageInfo> {
    /**
     * 发送消息
     * @param req
     */
    public Result<MessageInfo> sendMsg(RequestContext<MessageInfo> req);
    /**
     * 发送消息
     * @param req
     */
    public Result<MessageInfo> sendGroupMsg(RequestContext<SendGroupMessageReq> req);
    /**
     * 获取消息
     * @param req
     */
    public Result<MessageInfo> getMsg(RequestContext req);

    /**
     * 读取消息
     * @param req
     * @return
     */
    public Result readMsg(RequestContext<MessageInfo> req);

    public Result<MessageInfo> sendFileMsg(RequestContext<SendFileMessageReq> req);

}
