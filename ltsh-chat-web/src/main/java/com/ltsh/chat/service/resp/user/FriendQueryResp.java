package com.ltsh.chat.service.resp.user;

import com.ltsh.chat.service.resp.WebResp;
import lombok.Data;

/**
 * Created by Random on 2017/10/23.
 */
@Data
public class FriendQueryResp extends WebResp {
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
    private Integer friendUserId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建用户
     */
    private Integer createBy;
}
