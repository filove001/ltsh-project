package com.ltsh.chat.service.impl;

import com.ltsh.chat.service.api.RedisService;
import com.ltsh.chat.service.api.UserService;
import com.ltsh.chat.service.config.GlobalConfig;
import com.ltsh.chat.service.dao.UserInfoDao;

import com.ltsh.chat.service.entity.UserInfo;
import com.ltsh.chat.service.enums.StatusEnums;
import com.ltsh.chat.service.enums.WebResultCode;
import com.ltsh.chat.service.req.ServiceReq;
import com.ltsh.chat.service.req.user.LoginVerifyReq;
import com.ltsh.chat.service.req.user.UserRegisterReq;

import com.ltsh.chat.service.utils.WebPasswordUtil;
import com.ltsh.common.client.redis.RedisKey;
import com.ltsh.common.entity.RequestContext;

import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.util.beetsql.entity.UserToken;
import com.ltsh.util.beetsql.entity.req.BaseReq;
import com.ltsh.util.beetsql.entity.result.ContentResult;
import com.ltsh.util.beetsql.impl.BaseServiceImpl;
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

    @Autowired
    private RedisService redisService;
    /**
     * 注册
     * @param req
     * @return
     */
    public ContentResult register(BaseReq<UserRegisterReq> req) {

        UserRegisterReq content = req.getContent();
        UserInfo searchUser = new UserInfo();
        searchUser.setLoginName(content.getLoginName());
        List<UserInfo> template = userInfoDao.template(searchUser);
        if(!template.isEmpty()) {
            return new ContentResult(WebResultCode.CF_FORMAT, "用户");

        }
        UserInfo userInfo = new UserInfo();
        userInfo.setCreateBy(0);
        userInfo.setCreateTime(new Date());
        userInfo.setLoginName(content.getLoginName());
        userInfo.setName(content.getNickName());
        userInfo.setNickName(content.getNickName());
        String password = WebPasswordUtil.createPassword(content.getPassword());
        if(password == null) {
            return new ContentResult(WebResultCode.SB_FORMAT, "注册用户");
        }
        userInfo.setPassword(password);
        userInfo.setStatus(StatusEnums.KY.getValue());
        userInfoDao.insert(userInfo);
        return new ContentResult(WebResultCode.CG);
}
    @Override
    public ContentResult<UserToken> loginQuery(ServiceReq req) {
        String value = redisService.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, req.getMedium(), req.getUserToken().getToken()));
        if(value != null) {
            UserToken userToken = JsonUtils.fromJson(value, UserToken.class);
            return new ContentResult<UserToken>(WebResultCode.CG, userToken);
        } else {
            return new ContentResult<UserToken>(WebResultCode.TOKEN_SX);
        }
    }

    @Override
    public ContentResult<UserToken> loginVerify(ServiceReq<LoginVerifyReq> req) {
        //模板查询
        UserInfo query = new UserInfo();
        LoginVerifyReq content = req.getContent();
        query.setLoginName(content.getLoginName());
        List<UserInfo> list = userInfoDao.template(query);
        if(list.isEmpty()) {
            return new ContentResult<>(WebResultCode.YZ_ZH_SB);
        }
        UserInfo userInfo = list.get(0);
        String dbPassword = userInfo.getPassword();
        String passwordRandomKey = content.getPasswordRandomKey();
        String passwordRandomValue = redisService.getAndDel(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, req.getMedium(), passwordRandomKey));
        if(!WebPasswordUtil.verify(content.getPassword(), dbPassword, passwordRandomValue)) {
            return new ContentResult<>(WebResultCode.YZ_ZH_SB);
        }
        UserToken userToken = new UserToken();
        userToken.setToken(StringUtils.getUUID());
        userToken.setUserId(userInfo.getId());
        userToken.setLoginTime(new Date());
        userToken.setUserName(userInfo.getName());
        userToken.setLoginName(userInfo.getLoginName());
        userToken.setNickName(userInfo.getNickName());
        String token = redisService.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getLoginName()));
        if(token != null) {
            redisService.del(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, token));
        }
        redisService.set(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getLoginName()), userToken.getToken(), GlobalConfig.getLong("tokenTies"));
        redisService.set(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, userToken.getToken()), userToken, GlobalConfig.getLong("tokenTies"));

        return new ContentResult<UserToken>(WebResultCode.CG, userToken);
    }


}
