package com.ltsh.chat.service.impl;


import com.ltsh.chat.service.api.BaseService;
import com.ltsh.chat.service.api.MessageService;
import com.ltsh.chat.service.api.UserGroupRelService;
import com.ltsh.chat.service.dao.*;
import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.entity.MessageInfoFile;
import com.ltsh.chat.service.entity.UserGroupRel;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.req.message.MessageSendFileReq;
import com.ltsh.chat.service.req.message.MessageSendGroupReq;

import com.ltsh.chat.service.resp.Result;
import com.ltsh.common.client.activemq.ActiveMQUtils;
import com.ltsh.common.entity.ToKenContext;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.utils.BeanUtils;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//    @Autowired
//    private UserGroupRelService userGroupRelService;
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
        this.baseDao = messageDao;
    }

    @Override
    public Result<MessageInfo> sendMsg(ToKenContext<MessageInfo> req) {
        MessageInfo entity = req.getContent();
//        BeanUtils.copyProperties(req, entity);
        entity.setCreateTime(new Date());
        entity.setCreateBy(req.getUserToken().getId());
        entity.setSendUser(req.getUserToken().getId());
        entity.setStatus(StatusEnums.FSZ.getValue());
        if(StringUtils.isEmpty(entity.getSourceType())) {
            entity.setSourceType("USER");
            entity.setSourceId(req.getUserToken().getId() + "");
        }

        messageDao.insert(entity, true);
        try {
            LogUtils.info("发送消息内容为:{}", JsonUtils.toJson(entity));
            activeMQUtils.sendMessage(entity.getToUser() + "",entity);
            return new Result<MessageInfo>(entity);
        } catch (Exception e) {
            LogUtils.error("发送消息失败!", e);
        }
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getFormatMessage("发送消息"));
    }
    @Override
    public Result<MessageInfo> sendFileMsg(ToKenContext<MessageSendFileReq> req) {

        ToKenContext<MessageInfo> sendMsgReq = new ToKenContext<>();
        BeanUtils.copyProperties(req, sendMsgReq);
        MessageInfo messageInfo = new MessageInfo();
        BeanUtils.copyProperties(req.getContent(), messageInfo);
        sendMsgReq.setContent(messageInfo);
        Result<MessageInfo> messageInfoResult = this.sendMsg(sendMsgReq);
        if(messageInfoResult.isSucceed()) {
            MessageInfo resultMessage = messageInfoResult.getContent();
            MessageInfoFile messageInfoFile = new MessageInfoFile();
            messageInfoFile.setFilePath(req.getContent().getFilePath());
            messageInfoFile.setMessageId(resultMessage.getId());
            messageInfoFile.setCreateBy(req.getUserToken().getId());
            messageInfoFile.setCreateTime(new Date());
            messageInfoFileDao.insert(messageInfoFile, true);
        }
        return messageInfoResult;
    }
    /**
     * 发送消息
     * @param req
     */
    public Result sendGroupMsg(ToKenContext<MessageSendGroupReq> req) {
        MessageSendGroupReq content = req.getContent();
        Integer groupId = content.getGroupId();
        UserGroupRel userGroupRel = new UserGroupRel();
        userGroupRel.setGroupId(groupId);
//        ToKenContext<MessageInfo> sendMsgReq = new ToKenContext<>();
//        req.setAll(sendMsgReq);
        List<UserGroupRel> list = userGroupRelDao.getSQLManager().template(userGroupRel);
        for (UserGroupRel userGroupRel1 : list) {
            ToKenContext<MessageInfo> sendMsgReq = new ToKenContext<>();
            BeanUtils.copyProperties(req, sendMsgReq);
            MessageInfo messageInfo = new MessageInfo();
            BeanUtils.copyProperties(req.getContent(), messageInfo);

            messageInfo.setToUser(userGroupRel1.getUserId());
            messageInfo.setSourceId(groupId + "");
            messageInfo.setSourceType("GROUP");
            sendMsgReq.setContent(messageInfo);
            Result result = this.sendMsg(sendMsgReq);
//            if(result.get)
        }
        return new Result(ResultCodeEnum.SUCCESS.getCode());
    }


    @Override
    public Result<MessageInfo> getMsg(ToKenContext req) {
        try {
            MessageInfo messageInfo = activeMQUtils.getMessage(req.getUserToken().getId() + "", MessageInfo.class);
            if(messageInfo != null) {
                LogUtils.info("获取消息内容为:{}", JsonUtils.toJson(messageInfo));
                messageInfo.setStatus(StatusEnums.YSD.getValue());
                messageDao.updateById(messageInfo);
            }
            return new Result<MessageInfo>(messageInfo);
        } catch (Exception e) {
            LogUtils.error("获取消息失败!", e);
        }
        return new Result(ResultCodeEnum.FAIL.getCode(), ResultCodeEnum.FAIL.getFormatMessage("获取消息"));
    }
    @Override
    public Result readMsg(ToKenContext<MessageInfo> req) {
        messageDao.executeUpdate("update message_info set status='YD' where status ='YSD' create_by=? and to_user=?", new Object[]{req.getContent().getCreateBy(), req.getUserToken().getId()});
        return new Result(ResultCodeEnum.SUCCESS);

    }
}

