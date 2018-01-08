package com.ltsh.chat.web.common.utils;

import com.ltsh.chat.web.common.annotation.CheckLogin;
import com.ltsh.chat.web.common.req.AppContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import com.ltsh.chat.web.common.enums.CheckLoginType;
import com.ltsh.common.client.redis.RedisKey;
import com.ltsh.common.client.redis.RedisUtil;
import com.ltsh.common.entity.UserToken;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.StringUtils;


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
            UserToken userToken = JsonUtils.fromJson(tokenStr, UserToken.class);
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
