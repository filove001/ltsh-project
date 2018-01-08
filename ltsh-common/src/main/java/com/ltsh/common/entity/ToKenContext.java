package com.ltsh.common.entity;

import com.ltsh.common.utils.BeanUtils;
import lombok.Data;

/**
 * token请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class ToKenContext<T> extends ApiContext {
    /**
     * token
     */
    private String token;
    /**
     * 用户token
     */
    private UserToken userToken;
    /**
     * 参数
     */
    private T content;


}
