package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.MessageInfoFileService;

import com.ltsh.chat.service.dao.MessageInfoFileDao;
import com.ltsh.chat.service.entity.MessageInfoFile;

import com.ltsh.util.beetsql.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by fengjianbo on 2018-01-05 11:50:30.
 */
@Service
public class MessageInfoFileServiceImpl extends BaseServiceImpl<MessageInfoFile> implements MessageInfoFileService {

    private MessageInfoFileDao messageInfoFileDao;
    @Autowired
    public void setMessageInfoFileDao(MessageInfoFileDao messageInfoFileDao) {
        this.messageInfoFileDao = messageInfoFileDao;
        this.baseDao = messageInfoFileDao;
    }

}
