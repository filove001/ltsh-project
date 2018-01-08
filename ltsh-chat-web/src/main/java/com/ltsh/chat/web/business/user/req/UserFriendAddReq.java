package com.ltsh.chat.web.business.user.req;

import com.ltsh.chat.web.common.req.AppContext;
import lombok.Data;

/**
 * Created by Random on 2017/11/13.
 */
@Data
public class UserFriendAddReq extends AppContext {
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
