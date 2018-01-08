package com.ltsh.chat.service.req.friend;

import com.ltsh.common.entity.ToKenContext;
import lombok.Data;

/**
 * Created by Random on 2017/11/9.
 */
@Data
public class UserFriendAddReq extends ToKenContext {
    /**
     * 添加好友id
     */
    private String loginName;
    /**
     * 备注名称
     */
    private String name;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 排序
     */
    private Integer sort;
}
