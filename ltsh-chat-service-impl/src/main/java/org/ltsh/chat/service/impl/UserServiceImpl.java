package org.ltsh.chat.service.impl;

import org.ltsh.chat.service.api.UserService;
import org.ltsh.chat.service.dao.UserInfoDao;
import org.ltsh.chat.service.entity.UserInfo;
import org.ltsh.chat.service.enums.ResultCodeEnum;
import org.ltsh.chat.service.req.user.LoginQueryServiceReq;
import org.ltsh.chat.service.req.user.LoginVerifyServiceReq;
import org.ltsh.chat.service.req.user.RandomServiceStrGetReq;
import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.service.resp.user.RandomStrGetResp;
import org.ltsh.common.client.redis.RedisKey;
import org.ltsh.common.client.redis.RedisUtil;
import org.ltsh.common.entity.UserToken;
import org.ltsh.common.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Random on 2017/10/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public Result<UserToken> loginQuery(LoginQueryServiceReq req) {
        String s = RedisUtil.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, req.getMedium(), req.getToken()));
        if(s != null) {
            UserToken userToken = JsonUtil.fromJson(s, UserToken.class);
            return new Result<>(userToken);
        } else {
            return new Result<>(ResultCodeEnum.TOKEN_FAIL);
        }
    }

    @Override
    public Result<UserToken> loginVerify(LoginVerifyServiceReq req) {
        //模板查询
        UserInfo query = new UserInfo();
        query.setLoginName(req.getLoginName());
        List<UserInfo> list = userInfoDao.template(query);
        if(list.isEmpty()) {
            return new Result<>(ResultCodeEnum.LOGIN_FAIL);
        }
        UserInfo userInfo = list.get(0);
        String password = userInfo.getPassword();
        String randomKey = req.getRandomKey();
        String randomValue = RedisUtil.get(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey));
        String encoder = MD5Util.encoder("ltshChat:" + password + randomValue);
        if(!req.getPassword().equals(encoder)) {
            return new Result<>(ResultCodeEnum.LOGIN_FAIL);
        }
        UserToken userToken = new UserToken(userInfo.getId(), userInfo.getLoginName(), userInfo.getName(), userInfo.getPhone(), StringUtils.getUUID());
        String token = RedisUtil.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getLoginName()));
        if(token != null) {
            RedisUtil.del(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, token));
        }
        RedisUtil.set(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getLoginName()), userToken.getToken(), 24*60*60);
        RedisUtil.set(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getToken()), userToken, 24*60*60);

        return new Result<>(userToken);
    }

    @Override
    public Result<RandomStrGetResp> getRandomStr(RandomServiceStrGetReq req) {

        String randomKey = StringUtils.getRandomString(6);
        while(RedisUtil.get(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey)) != null) {
            randomKey = StringUtils.getRandomString(6);
        }
        String randomValue = StringUtils.getRandomString(8);
        RedisUtil.set(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey), randomValue, 3*60);
        RandomStrGetResp resp = new RandomStrGetResp();
        try {
            resp.setRandomKey(AES.encrypt(randomKey, req.getUuid()));
            resp.setRandomValue(AES.encrypt(randomValue, req.getUuid()));
            return new Result<>(resp);
        } catch (Exception e) {
            LogUtils.error(e.getMessage(), e);
            return new Result(ResultCodeEnum.FAIL.getCode(), e.getMessage());
        }
    }
}
