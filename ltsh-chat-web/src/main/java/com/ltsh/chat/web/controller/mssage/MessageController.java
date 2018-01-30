package com.ltsh.chat.web.controller.mssage;


import com.ltsh.chat.service.api.MessageInfoFileService;
import com.ltsh.chat.service.api.MessageService;

import com.ltsh.chat.service.entity.MessageInfo;
import com.ltsh.chat.service.req.message.SendFileMessageReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.common.annotation.CheckLogin;
import com.ltsh.chat.web.common.controller.BaseController;

import com.ltsh.chat.web.common.req.AppContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
    public Result sendMessage(AppContext<MessageInfo> req){
        return messageService.sendMsg(req);
    }
    @ResponseBody
    @RequestMapping("/sendFileMessage")
    @CheckLogin
    public Result sendFileMessage(AppContext<SendFileMessageReq> req, MultipartHttpServletRequest multiReq){
//        MultipartFile multipartFile = multiReq.getFile("file");
//        File file = new File(uploadFile);
//        if(file.exists())
//            file.mkdirs();
//        String originalFilename = multipartFile.getOriginalFilename();
//
//        String fileName = StringUtils.getUUID() + multipartFile.getOriginalFilename().lastIndexOf(".");
//
//        ToKenContext<MessageInfo> toKenContext = new ToKenContext<>();
//        MessageInfo messageInfo = new MessageInfo();
//
//        BeanUtils.copyProperties(req, toKenContext);
//        BeanUtils.copyProperties(req, messageInfo);
//        toKenContext.setContent(messageInfo);
//        return messageService.sendFileMsg(req);
        return null;
    }
    @ResponseBody
    @RequestMapping("/getMessage")
    @CheckLogin
    public Result<MessageInfo> getMessage(AppContext req){
        return messageService.getMsg(req);
    }
    @ResponseBody
    @RequestMapping("/readMessage")
    @CheckLogin
    public Result readMessage(AppContext<MessageInfo> req){
        return messageService.readMsg(req);
    }
}
