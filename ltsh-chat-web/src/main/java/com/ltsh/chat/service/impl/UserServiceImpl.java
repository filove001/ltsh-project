package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.UserService;
import com.ltsh.chat.service.config.GlobalConfig;
import com.ltsh.chat.service.dao.UserInfoDao;
import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.req.user.LoginVerifyReq;
import com.ltsh.chat.service.req.user.UserRegisterReq;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.service.utils.PasswordUtils;
import com.ltsh.common.client.redis.RedisKey;
import com.ltsh.common.client.redis.RedisUtil;
import com.ltsh.common.entity.RequestContext;
import com.ltsh.common.entity.UserToken;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.StringUtils;
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
    public Result register(RequestContext<UserRegisterReq> req) {
        UserRegisterReq content = req.getContent();
        UserInfo searchUser = new UserInfo();
        searchUser.setLoginName(content.getLoginName());
        List<UserInfo> template = userInfoDao.template(searchUser);
        if(!template.isEmpty()) {
            return new Result(ResultCodeEnum.REPETITION, "用户");

        }
        UserInfo userInfo = new UserInfo();
        userInfo.setCreateBy(0);
        userInfo.setCreateTime(new Date());
        userInfo.setLoginName(content.getLoginName());
        userInfo.setName(content.getNickName());
        userInfo.setNickName(content.getNickName());
        userInfo.setPassword(PasswordUtils.createPassword(content.getPassword()));
        userInfo.setStatus(StatusEnums.KY.getValue());
        userInfoDao.insert(userInfo);
        return new Result();
}
    @Override
    public Result<UserToken> loginQuery(RequestContext req) {
        String s = RedisUtil.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, req.getMedium(), req.getToken()));
        if(s != null) {
            UserToken userToken = JsonUtils.fromJson(s, UserToken.class);
            return new Result<>(userToken);
        } else {
            return new Result<>(ResultCodeEnum.TOKEN_FAIL);
        }
    }

    @Override
    public Result<UserToken> loginVerify(RequestContext<LoginVerifyReq> req) {
        //模板查询
        UserInfo query = new UserInfo();
        LoginVerifyReq content = req.getContent();
        query.setLoginName(content.getLoginName());
        List<UserInfo> list = userInfoDao.template(query);
        if(list.isEmpty()) {
            return new Result<>(ResultCodeEnum.LOGIN_FAIL);
        }
        UserInfo userInfo = list.get(0);
        String password = userInfo.getPassword();
        String randomKey = req.getRandomKey();
        String randomValue = RedisUtil.get(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), randomKey));
        if(!PasswordUtils.verify(content.getPassword(), password, randomValue)) {
            return new Result<>(ResultCodeEnum.LOGIN_FAIL);
        }
        UserToken userToken = new UserToken(userInfo.getId(), userInfo.getLoginName(), userInfo.getName(), userInfo.getPhone(), StringUtils.getUUID());
        String token = RedisUtil.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getLoginName()));
        if(token != null) {
            RedisUtil.del(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, token));
        }
        RedisUtil.set(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getLoginName()), userToken.getToken(), GlobalConfig.getInt("tokenTies"));
        RedisUtil.set(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getToken()), userToken, GlobalConfig.getInt("tokenTies"));

        return new Result<>(userToken);
    }


}
