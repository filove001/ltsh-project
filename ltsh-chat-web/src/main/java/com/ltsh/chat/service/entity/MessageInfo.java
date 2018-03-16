package com.ltsh.chat.service.entity;

import com.ltsh.util.beetsql.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


/**
 * 消息记录
 * Created by Random on 2017/10/23.
 */
@Data
@ToString
public class MessageInfo extends BaseEntity {

    /**
     * 消息内容
     */
    private String msgContext;

    /**
     * 模板id
     */
    private Integer templateId;
    /**
     * 消息类型
     */
    private Integer msgType;
    /**
     * 发送人
     */
    private Integer sendUser;
    /**
     * 接受人
     */
    private Integer toUser;

    /**
     * 发送类型
     */
    private Integer sendType;
    /**
     * 发送时间
     */
    private Date sendDate;
    /**
     * 读取时间
     */
    private Date readTime;

    /**
     * 状态
     */
    private String status;
    /**
     * 来源id
     */
    private String sourceId;
    /**
     * 来源类型
     */
    private String sourceType;

}
