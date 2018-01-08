package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.req.message.MessageSendFileReq;
import com.ltsh.chat.service.req.message.MessageSendGroupReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.entity.ToKenContext;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
public interface MessageService extends BaseService<MessageInfo> {
    /**
     * 发送消息
     * @param req
     */
    public Result<MessageInfo> sendMsg(ToKenContext<MessageInfo> req);
    /**
     * 发送消息
     * @param req
     */
    public Result<MessageInfo> sendGroupMsg(ToKenContext<MessageSendGroupReq> req);
    /**
     * 获取消息
     * @param req
     */
    public Result<MessageInfo> getMsg(ToKenContext req);

    /**
     * 读取消息
     * @param req
     * @return
     */
    public Result readMsg(ToKenContext<MessageInfo> req);

    public Result<MessageInfo> sendFileMsg(ToKenContext<MessageSendFileReq> req);

}
