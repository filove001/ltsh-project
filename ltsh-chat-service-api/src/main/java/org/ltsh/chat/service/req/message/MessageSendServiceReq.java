package org.ltsh.chat.service.req.message;

import lombok.Data;
import org.ltsh.common.entity.ToKenContext;

import java.util.List;

/**
 * Created by Random on 2017/10/11.
 */
@Data
public class MessageSendServiceReq extends ToKenContext {
    private Integer id;
    /**
     * 消息内容
     */
    private String msgContext;
    /**
     * 图片List
     */
    private List<String> imageList;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 消息类型
     */
    private String msgType;
    /**
     * 发送人
     */
    private String formUser;
    /**
     * 发送人姓名
     */
    private String formUserName;
    /**
     * 接受人
     */
    private Integer toUser;
    /**
     * 接受人姓名
     */
    private String toUserName;
    /**
     * 发送类型
     */
    private String sendType;
    /**
     * 发送时间
     */
    private String sendDate;
    /**
     * 短信标志
     */
    private String flag;
}
