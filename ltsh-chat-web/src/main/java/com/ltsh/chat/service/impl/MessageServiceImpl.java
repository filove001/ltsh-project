package com.ltsh.chat.service.impl;


import com.ltsh.chat.service.api.MessageService;
import com.ltsh.chat.service.dao.*;
import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.entity.MessageInfoFile;
import com.ltsh.chat.service.entity.UserGroupRel;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.enums.WebResultCode;

import com.ltsh.chat.service.req.message.SendFileMessageReq;
import com.ltsh.chat.service.req.message.SendGroupMessageReq;


import com.ltsh.common.client.activemq.ActiveMQUtils;
import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.utils.BeanUtils;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.util.beetsql.entity.req.BaseReq;
import com.ltsh.util.beetsql.entity.result.ContentResult;
import com.ltsh.util.beetsql.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.jms.ConnectionConsumer;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Session;
import java.util.Date;
import java.util.List;

/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl<MessageInfo> implements MessageService {
    @Autowired
    private ActiveMQUtils activeMQUtils;

    private MessageDao messageDao;

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UserGroupDao userGroupDao;
    @Autowired
    private UserGroupRelDao userGroupRelDao;
    @Autowired
    private MessageInfoFileDao messageInfoFileDao;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
//    @Autowired
//    private UserGroupRelService userGroupRelService;
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.baseDao = messageDao;
    }

    @Override
    public ContentResult<MessageInfo> sendMsg(BaseReq<MessageInfo> req) {

        return sendMessage(req.getContent(), req.getUserToken().getUserId());
    }



    @Override
    public ContentResult<MessageInfo> sendFileMsg(BaseReq<SendFileMessageReq> req) {
        ContentResult<MessageInfo> messageInfoResult = sendMessage(req.getContent(), req.getUserToken().getUserId());
        if(messageInfoResult.chengGong()) {
            MessageInfo resultMessage = messageInfoResult.getContent();
            MessageInfoFile messageInfoFile = new MessageInfoFile();
            messageInfoFile.setFilePath(req.getContent().getFilePath());
            messageInfoFile.setMessageId(resultMessage.getId());
            messageInfoFile.setCreateBy(req.getUserToken().getUserId());
            messageInfoFile.setCreateTime(new Date());
            messageInfoFileDao.insert(messageInfoFile, true);
        }
        return messageInfoResult;
    }
    /**
     * 发送消息
     * @param req
     */
    public ContentResult sendGroupMsg(BaseReq<SendGroupMessageReq> req) {
        SendGroupMessageReq content = req.getContent();
        Integer groupId = content.getGroupId();
        UserGroupRel userGroupRel = new UserGroupRel();
        userGroupRel.setGroupId(groupId);
//        ToKenContext<MessageInfo> sendMsgReq = new ToKenContext<>();
//        req.setAll(sendMsgReq);
        List<UserGroupRel> list = userGroupRelDao.getSQLManager().template(userGroupRel);
        for (UserGroupRel userGroupRel1 : list) {
            MessageInfo messageInfo = new MessageInfo();
            BeanUtils.copyProperties(req.getContent(), messageInfo);

            messageInfo.setToUser(userGroupRel1.getUserId());
            messageInfo.setSourceId(groupId + "");
            messageInfo.setSourceType("GROUP");
            ContentResult result = this.sendMessage(messageInfo, userGroupRel1.getUserId());
            return result;
//            if(result.get)
        }
        return new ContentResult(WebResultCode.CG);
    }


    @Override
    public ContentResult<MessageInfo> getMsg(BaseReq req) {
        try {
            Session session = jmsMessagingTemplate.getConnectionFactory().createConnection().createSession(true, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("");
            session.createConsumer(queue);
//            ConnectionConsumer connectionConsumer = jmsMessagingTemplate.getConnectionFactory().createConnection().createConnectionConsumer();

//            jmsMessagingTemplate.receive()
            Message<?> receive = jmsMessagingTemplate.receive(getQueueName(req.getUserToken().getUserId() + ""));
            MessageInfo payload = (MessageInfo) receive.getPayload();

            MessageInfo messageInfo = activeMQUtils.getMessage(req.getUserToken().getUserId() + "", MessageInfo.class);
            if(messageInfo != null) {
                LogUtils.info("获取消息内容为:{}", JsonUtils.toJson(messageInfo));
                messageInfo.setStatus(StatusEnums.YSD.getValue());
                messageDao.updateById(messageInfo);
            }
            return new ContentResult<MessageInfo>(WebResultCode.CG, messageInfo);
        } catch (Exception e) {
            LogUtils.error("获取消息失败!", e);
        }
        return new ContentResult(WebResultCode.SB_FORMAT.getCode(), WebResultCode.SB_FORMAT.getFormatMessage("获取消息"));
    }
    @Override
    public ContentResult readMsg(BaseReq<MessageInfo> req) {
        messageDao.executeUpdate("update message_info set status='YD' where status ='YSD' create_by=? and to_user=?", new Object[]{req.getContent().getCreateBy(), req.getUserToken().getUserId()});
        return new ContentResult(WebResultCode.CG);

    }

    /**
     * 发送消息
     * @param messageInfo 消息实体
     * @param userId 发送用户id
     * @return
     */
    private ContentResult<MessageInfo> sendMessage(MessageInfo messageInfo, Integer userId) {
//        BeanUtils.copyProperties(req, entity);
        messageInfo.setCreateTime(new Date());
        messageInfo.setCreateBy(userId);
        messageInfo.setSendUser(userId);
        messageInfo.setStatus(StatusEnums.FSZ.getValue());
        if(StringUtils.isEmpty(messageInfo.getSourceType())) {
            messageInfo.setSourceType("USER");
            messageInfo.setSourceId(userId + "");
        }

        messageDao.insert(messageInfo, true);
        try {
            LogUtils.info("发送消息内容为:{}", JsonUtils.toJson(messageInfo));
            jmsMessagingTemplate.convertAndSend(getQueueName(messageInfo.getToUser() + ""), messageInfo);
//            activeMQUtils.sendMessage(messageInfo.getToUser() + "",messageInfo);
            return new ContentResult<MessageInfo>(WebResultCode.CG, messageInfo);
        } catch (Exception e) {
            LogUtils.error("发送消息失败!", e);
        }
        return new ContentResult<>(WebResultCode.SB_FORMAT.getCode(), WebResultCode.SB_FORMAT.getFormatMessage("发送消息"));
    }
    private final static String QUEUE_PRE = "QUEUE_PRE_";
    private String getQueueName(String userCode){
        return QUEUE_PRE + userCode;
    }
}

