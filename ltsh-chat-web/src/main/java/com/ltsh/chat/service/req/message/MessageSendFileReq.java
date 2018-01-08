package com.ltsh.chat.service.req.message;

import com.ltsh.chat.service.entity.MessageInfo;
import lombok.Data;

/**
 * Created by fengjianbo on 2017/12/28.
 */
@Data
public class MessageSendFileReq extends MessageInfo {
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 文件类型
     */
    private String fileType;
}
