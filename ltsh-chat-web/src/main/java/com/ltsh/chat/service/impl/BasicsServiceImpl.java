package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.BasicsService;
import com.ltsh.chat.service.api.RedisService;
import com.ltsh.chat.service.config.GlobalConfig;
import com.ltsh.chat.service.enums.WebResultCode;
import com.ltsh.chat.service.req.ServiceReq;
import com.ltsh.chat.service.resp.basics.RandomResp;
import com.ltsh.common.client.redis.RedisKey;

import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.security.AES;
import com.ltsh.util.beetsql.entity.result.ContentResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fengjianbo on 2018/1/30.
 */
@Service
public class BasicsServiceImpl implements BasicsService {
    @Autowired
    private RedisService redisService;
    @Override
    public ContentResult<RandomResp> getRandomStr(ServiceReq<String> req) {

        String randomKey = StringUtils.getRandomString(6);

        while(redisService.get(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey)) != null) {
            randomKey = StringUtils.getRandomString(6);
        }
        String randomValue = StringUtils.getRandomString(8);
        redisService.set(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey), randomValue, GlobalConfig.getLong("randomTimes"));
        RandomResp resp = new RandomResp();
        try {
            resp.setRandomKey(AES.encrypt(randomKey, req.getContent()));
            resp.setRandomValue(AES.encrypt(randomValue, req.getContent()));
            return new ContentResult<RandomResp>(WebResultCode.CG, resp);
        } catch (Exception e) {
            LogUtils.error(e.getMessage(), e);
            return new ContentResult(WebResultCode.SB_FORMAT.getCode(), e.getMessage());
        }
    }
}
