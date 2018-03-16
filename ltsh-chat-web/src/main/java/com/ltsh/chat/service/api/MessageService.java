package com.ltsh.chat.service.api;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.req.message.SendFileMessageReq;
import com.ltsh.chat.service.req.message.SendGroupMessageReq;
import com.ltsh.common.entity.RequestContext;
import com.ltsh.util.beetsql.api.BaseService;
import com.ltsh.util.beetsql.entity.req.BaseReq;
import com.ltsh.util.beetsql.entity.result.ContentResult;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
public interface MessageService extends BaseService<MessageInfo> {
    /**
     * 发送消息
     * @param req
     */
    public ContentResult<MessageInfo> sendMsg(BaseReq<MessageInfo> req);
    /**
     * 发送消息
     * @param req
     */
    public ContentResult<MessageInfo> sendGroupMsg(BaseReq<SendGroupMessageReq> req);
    /**
     * 获取消息
     * @param req
     */
    public ContentResult<MessageInfo> getMsg(BaseReq req);

    /**
     * 读取消息
     * @param req
     * @return
     */
    public ContentResult readMsg(BaseReq<MessageInfo> req);

    public ContentResult<MessageInfo> sendFileMsg(BaseReq<SendFileMessageReq> req);

}
