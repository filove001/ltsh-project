package com.ltsh.chat.service.entity;

import com.ltsh.util.beetsql.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 用户好友
 * Created by Random on 2017/10/23.
 */
@Data
@ToString
public class UserFriend extends BaseEntity {
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

}
