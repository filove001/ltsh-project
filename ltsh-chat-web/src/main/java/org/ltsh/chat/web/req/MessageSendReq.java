package org.ltsh.chat.web.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Random on 2017/07/26.
 */
@Data
public class MessageSendReq extends AppContext {
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
    private Integer sendUser;
    /**
     * 发送人姓名
     */
    private String sendUserName;
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
