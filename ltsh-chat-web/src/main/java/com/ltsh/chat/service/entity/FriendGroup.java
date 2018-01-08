package com.ltsh.chat.service.entity;

import lombok.Data;
import lombok.ToString;

/**
 * 好友分组
 * Created by Random on 2017/10/23.
 */
@Data
@ToString
public class FriendGroup extends BaseEntity {
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
     * 排序
     */
    private Integer sort;

}
