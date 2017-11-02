package org.ltsh.chat.service.resp;

import lombok.Data;

/**
 * Created by Random on 2017/10/23.
 */
@Data
public class FriendQueryResp extends BaseResp {
    /**
     * 名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 状态
     */
    private String status;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 好友用户id
     */
    private String friendUserId;
    /**
     * 排序
     */
    private Integer sort;
}
