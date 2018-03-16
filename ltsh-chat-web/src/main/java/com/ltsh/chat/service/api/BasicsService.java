package com.ltsh.chat.service.api;

import com.ltsh.chat.service.req.ServiceReq;
import com.ltsh.chat.service.resp.basics.RandomResp;

import com.ltsh.util.beetsql.entity.result.ContentResult;


/**
 * Created by fengjianbo on 2018/1/30.
 */
public interface BasicsService {

    public ContentResult<RandomResp> getRandomStr(ServiceReq<String> req);
}
