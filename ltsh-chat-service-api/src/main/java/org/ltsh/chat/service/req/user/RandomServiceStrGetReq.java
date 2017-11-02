package org.ltsh.chat.service.req.user;

import lombok.Data;
import org.ltsh.common.entity.ApiContext;

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
