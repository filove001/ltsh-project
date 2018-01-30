package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.BasicsService;
import com.ltsh.chat.service.config.GlobalConfig;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.resp.basics.RandomResp;
import com.ltsh.common.client.redis.RedisKey;
import com.ltsh.common.client.redis.RedisUtil;
import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.security.AES;
import org.springframework.stereotype.Service;

/**
 * Created by fengjianbo on 2018/1/30.
 */
@Service
public class BasicsServiceImpl implements BasicsService {

    @Override
    public Result<RandomResp> getRandomStr(RequestContext<String> req) {

        String randomKey = StringUtils.getRandomString(6);
        while(RedisUtil.get(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey)) != null) {
            randomKey = StringUtils.getRandomString(6);
        }
        String randomValue = StringUtils.getRandomString(8);
        RedisUtil.set(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey), randomValue, GlobalConfig.getInt("randomTimes"));
        RandomResp resp = new RandomResp();
        try {
            resp.setRandomKey(AES.encrypt(randomKey, req.getContent()));
            resp.setRandomValue(AES.encrypt(randomValue, req.getContent()));
            return new Result<>(resp);
        } catch (Exception e) {
            LogUtils.error(e.getMessage(), e);
            return new Result(ResultCodeEnum.FAIL.getCode(), e.getMessage());
        }
    }
}
