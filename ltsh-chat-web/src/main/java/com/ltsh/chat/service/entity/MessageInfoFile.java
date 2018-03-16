package com.ltsh.chat.service.entity;

import com.ltsh.util.beetsql.entity.BaseEntity;
import lombok.Data;

/**
 * Created by fengjianbo on 2018-01-05 11:50:30.
 */
@Data
public class MessageInfoFile extends BaseEntity {
    
        /**
         * 消息id
         **/
        private Integer messageId;
        
        /**
         * 文件路径
         **/
        private String filePath;
        
        /**
         * 本机文件路径
         **/
        private String localPath;
        
        /**
         * 文件类型
         **/
        private String fileType;
        
        /**
         * 原文件名称
         **/
        private String originalFilename;
    
}
