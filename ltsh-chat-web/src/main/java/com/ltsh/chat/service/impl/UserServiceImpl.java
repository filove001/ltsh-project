package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserService;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.req.user.LoginQueryServiceReq;
import com.ltsh.chat.service.req.user.RandomServiceStrGetReq;
import com.ltsh.chat.service.resp.user.RandomStrGetResp;
import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.req.user.LoginVerifyServiceReq;
import com.ltsh.chat.service.req.user.UserRegisterServiceReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.utils.PasswordUtils;
import com.ltsh.common.client.redis.RedisKey;
import com.ltsh.common.client.redis.RedisUtil;
import com.ltsh.common.entity.UserToken;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.security.AES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Random on 2017/10/11.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserInfo> implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;


    /**
     * 注册
     * @param req
     * @return
     */
    public Result register(UserRegisterServiceReq req) {
        UserInfo searchUser = new UserInfo();
        searchUser.setLoginName(req.getLoginName());
        List<UserInfo> template = userInfoDao.template(searchUser);
        if(!template.isEmpty()) {
            return new Result(ResultCodeEnum.REPETITION, "用户");

        }
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
