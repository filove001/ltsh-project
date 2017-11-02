package org.ltsh.chat.service.entity;

import lombok.Data;
import lombok.ToString;
import org.ltsh.common.utils.db.NoDbColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 图片List
     */
    @NoDbColumn
    private List<String> imageList;

}
