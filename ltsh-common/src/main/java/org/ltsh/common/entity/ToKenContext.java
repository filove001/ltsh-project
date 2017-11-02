package org.ltsh.common.entity;

import lombok.Data;

/**
 * token请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class ToKenContext extends ApiContext {
    /**
     * token
     */
    private String token;
    /**
     * 用户token
     */
    private UserToken userToken;
}
