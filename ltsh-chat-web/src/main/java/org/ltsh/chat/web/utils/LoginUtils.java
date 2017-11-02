package org.ltsh.chat.web.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.ltsh.chat.service.enums.ResultCodeEnum;
import org.ltsh.chat.service.resp.Result;
import org.ltsh.chat.web.annotation.CheckLogin;
import org.ltsh.chat.web.enums.CheckLoginType;
import org.ltsh.chat.web.req.AppContext;
import org.ltsh.common.client.redis.RedisKey;
import org.ltsh.common.client.redis.RedisUtil;
import org.ltsh.common.entity.UserToken;
import org.ltsh.common.utils.JsonUtil;
import org.ltsh.common.utils.StringUtils;

import java.lang.reflect.Method;

/**
 * Created by Random on 2017/10/11.
 */
public class LoginUtils {
    public static boolean isLogin(ProceedingJoinPoint point, AppContext appContext) {
        CheckLogin checkLogin = getCheckLogin(point);
        if(checkLogin==null){
            return true;
        }
        if(checkLogin.value()== CheckLoginType.CHECK){
            if(StringUtils.isEmpty(appContext.getToken())){
                return false;
            }
            String tokenStr = RedisUtil.get(RedisKey.getRedisKey(RedisKey.TOKEN_KEY, appContext.getToken()));
            if(StringUtils.isEmpty(tokenStr)){
                return false;
            }
            UserToken userToken = JsonUtil.fromJson(tokenStr, UserToken.class);
            appContext.setUserToken(userToken);
            return true;
        }
        return false;
    }

    private static CheckLogin getCheckLogin(ProceedingJoinPoint point){
        String methodName=point.getSignature().getName();
        Class<?> classTarget=point.getTarget().getClass();
        Class<?>[] par=((MethodSignature) point.getSignature()).getParameterTypes();
        Method objMethod = null;
        CheckLogin accessAuth = null;
        try {
            objMethod = classTarget.getMethod(methodName, par);
            accessAuth = objMethod.getAnnotation(CheckLogin.class);
            if(accessAuth==null){
                accessAuth = classTarget.getAnnotation(CheckLogin.class);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return accessAuth;
    }
}
