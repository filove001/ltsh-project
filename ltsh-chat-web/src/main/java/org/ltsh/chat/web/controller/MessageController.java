package org.ltsh.chat.web.controller;


import org.ltsh.chat.service.api.MessageService;

import org.ltsh.chat.service.req.message.MessageGetServiceReq;
import org.ltsh.chat.service.req.message.MessageSendServiceReq;
import org.ltsh.chat.service.resp.MessageGetServiceResp;
import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.web.annotation.CheckLogin;
import org.ltsh.chat.web.req.MessageGetReq;

import org.ltsh.chat.web.req.MessageSendReq;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Random on 2017/07/26.
 */
@Controller
@RequestMapping("/chat/message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @ResponseBody
    @RequestMapping("/sendMessage")
    @CheckLogin
    public Result<String> sendMessage(MessageSendReq req){
        MessageSendServiceReq sendMessageServiceReq = new MessageSendServiceReq();
        BeanUtils.copyProperties(req, sendMessageServiceReq);
        return messageService.sendMsg(sendMessageServiceReq);
    }
    @ResponseBody
    @RequestMapping("/getMessage")
    @CheckLogin
    public Result<MessageGetServiceResp> getMessage(MessageGetReq req){
        MessageGetServiceReq getMessageReq = new MessageGetServiceReq();
        BeanUtils.copyProperties(req, getMessageReq);
        return messageService.getMsg(getMessageReq);
    }
    @RequestMapping("/getMessageView")
    public String getMessageView(){
        return "getMessageView";
    }
    @RequestMapping("/sendMessageView")
    public String sendMessageView(){
        return "sendMessageView";
    }
    @RequestMapping("/chatView")
    public String chatView(){
        return "chatView";
    }
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }
}
