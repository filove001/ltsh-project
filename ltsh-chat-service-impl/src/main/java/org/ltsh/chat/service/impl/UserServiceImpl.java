package org.ltsh.chat.service.impl;

import org.ltsh.chat.service.api.UserService;
import org.ltsh.chat.service.dao.UserInfoDao;
import org.ltsh.chat.service.entity.UserInfo;
import org.ltsh.chat.service.enums.ResultCodeEnum;
import org.ltsh.chat.service.enums.StatusEnums;
import org.ltsh.chat.service.req.user.LoginQueryServiceReq;
import org.ltsh.chat.service.req.user.LoginVerifyServiceReq;
import org.ltsh.chat.service.req.user.RandomServiceStrGetReq;
import org.ltsh.chat.service.req.user.UserRegisterServiceReq;
import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.service.resp.user.RandomStrGetResp;
import org.ltsh.chat.service.utils.PasswordUtils;
import org.ltsh.common.client.redis.RedisKey;
import org.ltsh.common.client.redis.RedisUtil;
import org.ltsh.common.entity.UserToken;

import org.ltsh.common.util.JsonUtils;
import org.ltsh.common.util.LogUtils;
import org.ltsh.common.util.StringUtils;
import org.ltsh.common.util.security.AES;
import org.ltsh.common.util.security.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Random on 2017/10/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 注册
     * @param req
     * @return
     */
    public Result register(UserRegisterServiceReq req) {
        UserInfo userInfo = new UserInfo();
        userInfo.setCreateBy(0);
        userInfo.setCreateTime(new Date());
        userInfo.setLoginName(req.getLoginName());
        userInfo.setPassword(PasswordUtils.createPassword(req.getPassword()));
        userInfo.setStatus(StatusEnums.KY.getValue());
        userInfoDao.insert(userInfo);
        return new Result();
    }
    @Override
    public Result<UserToken> loginQuery(LoginQueryServiceReq req) {
        String s = RedisUtil.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, req.getMedium(), req.getToken()));
        if(s != null) {
            UserToken userToken = JsonUtils.fromJson(s, UserToken.class);
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
        if(!PasswordUtils.verify(req.getPassword(), password, randomValue)) {
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
