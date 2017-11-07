package org.ltsh.chat.service.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Random on 2017/10/23.
 */
@Data
@ToString
public class MessageGetServiceResp extends BaseResp {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建用户id
     */
    private Integer createBy;
    /**
     * 创建用户名称
     */
    private String createByName;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改用户
     */
    private Integer updateBy;
    /**
     * 修改用户名称
     */
    private String updateByName;
    /**
     * 修改时间
     */
    private String updateTime;
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
     * 发送人
     */
    private String sendUserName;
    /**
     * 接受人
     */
    private Integer toUser;
    /**
     * 接受人名称
     */
    private String toUserName;
    /**
     * 发送类型
     */
    private Integer sendType;
    /**
     * 发送时间
     */
    private String sendDate;
    /**
     * 读取时间
     */
    private String readTime;

    /**
     * 状态
     */
    private String status;
}
