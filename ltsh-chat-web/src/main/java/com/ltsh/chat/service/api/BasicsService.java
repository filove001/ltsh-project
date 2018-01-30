package com.ltsh.chat.service.api;

import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.resp.basics.RandomResp;
import com.ltsh.common.entity.RequestContext;


/**
 * Created by fengjianbo on 2018/1/30.
 */
public interface BasicsService {

    public Result<RandomResp> getRandomStr(RequestContext<String> req);
}
