package com.ltsh.chat.service.entity;

import com.ltsh.util.beetsql.entity.BaseEntity;
import lombok.Data;
import lombok.ToString;

/**
 * 好友关系
 * Created by Random on 2017/10/23.
 */
@Data
@ToString
public class FriendGroupRel extends BaseEntity {

    /**
     * 分组id
     */
    private Integer groupId;
    /**
     * 好友id
     */
    private Integer friendId;

    /**
     * 排序
     */
    private Integer sort;

}
