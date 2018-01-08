package com.ltsh.chat.service.req.user;

import com.ltsh.common.entity.ApiContext;
import lombok.Data;

/**
 * 随机数获取请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class RandomServiceStrGetReq extends ApiContext {

    /**
     * 请求uuid
     */
    private String uuid;

    /**
     * 设备媒体uuid
     */
    private String medium;

}
