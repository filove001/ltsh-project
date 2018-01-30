package com.ltsh.chat.service.resp.basics;

import com.ltsh.common.entity.ApiContext;
import com.ltsh.common.entity.BaseResp;
import lombok.Data;

/**
 * 随机数获取请求类
 * Created by Random on 2017/10/11.
 */
@Data
public class RandomResp extends BaseResp {
    /**
     * 随机数key
     */
    private String randomKey;
    /**
     * 随机数值
     */
    private String randomValue;
}
