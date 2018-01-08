package com.ltsh.chat.web.business.message.controller;


import com.ltsh.chat.service.api.MessageInfoFileService;
import com.ltsh.chat.service.api.MessageService;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.business.message.req.MessageGetReq;
import com.ltsh.chat.web.business.message.req.MessageReadReq;
import com.ltsh.chat.web.business.message.req.MessageSendReq;
import com.ltsh.chat.web.common.annotation.CheckLogin;
import com.ltsh.chat.web.common.controller.BaseController;

import com.ltsh.common.entity.ToKenContext;
import com.ltsh.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;

/**
 * Created by Random on 2017/07/26.
 */
@Controller
@RequestMapping("/chat/message")
public class MessageController extends BaseController {
    private String uploadFile = "file:D:\\test\\upload\\";
    @Autowired
    private MessageService messageService;
    @Autowired
    private MessageInfoFileService messageInfoFileService;
    @ResponseBody
    @RequestMapping("/sendMessage")
    @CheckLogin
    public Result sendMessage(MessageSendReq req){
        ToKenContext<MessageInfo> toKenContext = new ToKenContext<>();
        MessageInfo messageInfo = new MessageInfo();

        BeanUtils.copyProperties(req, toKenContext);
        BeanUtils.copyProperties(req, messageInfo);
        toKenContext.setContent(messageInfo);
        return messageService.sendMsg(toKenContext);
    }
    @ResponseBody
    @RequestMapping("/sendFileMessage")
    @CheckLogin
    public Result sendFileMessage(MessageSendReq req, MultipartHttpServletRequest multiReq){
        MultipartFile multipartFile = multiReq.getFile("file");
        File file = new File(uploadFile);
        if(file.exists())
            file.mkdirs();
        String originalFilename = multipartFile.getOriginalFilename();

        String fileName = StringUtils.getUUID() + multipartFile.getOriginalFilename().lastIndexOf(".");

        ToKenContext<MessageInfo> toKenContext = new ToKenContext<>();
        MessageInfo messageInfo = new MessageInfo();

        BeanUtils.copyProperties(req, toKenContext);
        BeanUtils.copyProperties(req, messageInfo);
        toKenContext.setContent(messageInfo);
        return messageService.sendMsg(toKenContext);
    }
    @ResponseBody
    @RequestMapping("/getMessage")
    @CheckLogin
    public Result<MessageInfo> getMessage(MessageGetReq req){
        ToKenContext toKenContext = new ToKenContext();
        BeanUtils.copyProperties(req, toKenContext);
        return messageService.getMsg(toKenContext);
    }
    @ResponseBody
    @RequestMapping("/readMessage")
    @CheckLogin
    public Result readMessage(MessageReadReq req){
        ToKenContext toKenContext = new ToKenContext();
        BeanUtils.copyProperties(req, toKenContext);
        return messageService.getMsg(toKenContext);
    }
}
