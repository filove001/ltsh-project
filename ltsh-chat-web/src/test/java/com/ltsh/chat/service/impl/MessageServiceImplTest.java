package com.ltsh.chat.service.impl;



import com.ltsh.chat.service.api.MessageService;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.enums.SendTypeEnums;
import com.ltsh.chat.service.req.message.SendGroupMessageReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.start.StartUp;
import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.entity.UserToken;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.StringUtils;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by fengjb-it on 2016/11/4 0004.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartUp.class)
//@ContextConfiguration(locations = "classpath:spring-context.xml")
public class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

//    @Test
    public void sendMsg() throws Exception {

        RequestContext<MessageInfo> context = new RequestContext<>();
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMsgType(0);
        messageInfo.setSendType(SendTypeEnums.SX.getValue());
        messageInfo.setMsgContext("你好啊");
        messageInfo.setToUser(1);
        context.setContent(messageInfo);
        UserToken userToken = new UserToken(2, "test2", "", "", StringUtils.getUUID());
        context.setUserToken(userToken);
        for (int i = 0; i < 10; i++) {
            messageInfo.setMsgContext("test2发来的消息" + i);
            messageService.sendMsg(context);
        }

    }
//    @Test
    public void getMsg() throws Exception {
        RequestContext context = new RequestContext();
        UserToken userToken = new UserToken(1, "test1", "", "", StringUtils.getUUID());
        context.setUserToken(userToken);
        Result<MessageInfo> msg = messageService.getMsg(context);
        System.out.println("结果:" + JsonUtils.toJson(msg));
    }
//    @Test
    public void sendGroupMsg() throws Exception {
        RequestContext<SendGroupMessageReq> context = new RequestContext<SendGroupMessageReq>();
        SendGroupMessageReq req = new SendGroupMessageReq();
        context.setContent(req);
        req.setMsgType(0);
        req.setSendType(SendTypeEnums.QZ.getValue());
        req.setGroupId(1);
        UserToken userToken = new UserToken(2, "test2", "", "", StringUtils.getUUID());
        context.setUserToken(userToken);
        req.setToUser(1);
//        req.setToUserName("test1");
        req.setMsgContext("你好啊");
        messageService.sendGroupMsg(context);
    }
}
