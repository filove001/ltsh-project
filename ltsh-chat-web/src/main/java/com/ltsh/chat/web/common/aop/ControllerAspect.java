package com.ltsh.chat.web.common.aop;



import com.ltsh.chat.service.api.RedisService;
import com.ltsh.chat.service.resp.PageResult;
import com.ltsh.chat.service.resp.Result;
import com.ltsh.chat.web.common.req.AppContext;
import com.ltsh.common.entity.BaseResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.joda.time.DateTime;
import org.joda.time.Duration;


import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.chat.web.common.exception.WebException;
import com.ltsh.chat.web.common.utils.LoginUtils;
import com.ltsh.common.client.redis.RedisKey;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.security.SignUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Created by wason on 2017/4/13.
 */
@Aspect
@Component
public class ControllerAspect {
    @Autowired
    private Validator validator;
    @Autowired
    private RedisService redisService;
    @Pointcut("execution(* com.ltsh.chat.web.controller.*.*.*(..))")
    private void controllerAspect(){}//定义一个切入点


    /**
     * 进入环绕通知
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            Object[] args = pjp.getArgs();
            String keep = StringUtils.getUUID();
            String flowKeep = keep;
            AppContext signObj = null;
            List list = new ArrayList();
            if(args.length > 0) {
                for (Object obj : args) {
                    if(obj instanceof HttpServletRequest) {
                        HttpServletRequest obj1 = (HttpServletRequest) obj;
                        if(obj1.getParameterMap().size() > 0) {
                            list.add(obj1.getParameterMap());
                        }
                    } else if(obj instanceof HttpServletResponse) {

                    } else {
                        if(obj instanceof AppContext) {
                            AppContext app = (AppContext) obj;
                            keep = app.getKeep();
                            if(!StringUtils.isEmpty(app.getFlowKeep())) {
                                flowKeep = app.getFlowKeep();
                            }

                            signObj = app;

                        }
                        list.add(obj);
                    }

                }
            }
            MDC.put("keep", keep);
            MDC.put("flowKeep", flowKeep);
            boolean isSkip = false;

            if(pjp.getSignature().getName().equals("getMessage") || pjp.getSignature().getName().equals("getRandomStr")) {
//                isSkip = true;
            }
            LogUtils.info("请求方法:{}.{},请求参数:{}",
                    pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(),
                    JsonUtils.toJsonLogStr(list, JsonUtils.getEncryption()));

            if(signObj != null) {
                Set<ConstraintViolation<Object>> validate = validator.validate(signObj);
                Iterator<ConstraintViolation<Object>> iterator = validate.iterator();
                StringBuffer stringBuffer = new StringBuffer();
                while (iterator.hasNext()) {
                    ConstraintViolation<Object> next = iterator.next();
                    stringBuffer.append(next.getPropertyPath()).append(":").append(next.getMessage()).append(",");
                }

                if(stringBuffer.length() > 0) {
                    throw new WebException(ResultCodeEnum.PARAM_CHACK_FAIL.getCode(), stringBuffer.toString());
                }

                String reqSignInfo = signObj.getSignInfo();
                String signStr = SignUtils.getSignStr(signObj);
                if(!isSkip) {
                    LogUtils.info("签名明文:{}", signStr);
                }

                String randomStr = "";
                if(signObj.getRandomKey() != null) {
                    randomStr = redisService.getAndDel(RedisKey.getRedisKey(RedisKey.RANDOM_KEY, signObj.getMedium(), signObj.getRandomKey()));
                }
                String signInfo = SignUtils.getSign(signStr, "123456", randomStr);
//            LogUtils.info("签名:{}", sign);
                if(!reqSignInfo.equals(signInfo)) {
                    throw new WebException(ResultCodeEnum.SIGN_FAIL);
                }
                boolean isLogin = LoginUtils.isLogin(pjp, signObj);
                if(!isLogin) {
                    throw new WebException(ResultCodeEnum.TOKEN_FAIL);
                }
            }


            DateTime begin = new DateTime();
            Object object = pjp.proceed();//执行该方法
            if(!isSkip) {
                LogUtils.info("返回参数:{}", JsonUtils.toJsonLogStr(object, JsonUtils.getEncryption()));
            }
            DateTime end = new DateTime();
            //计算区间毫秒数
            Duration d = new Duration(begin, end);
            long millis = d.getMillis();
            if(!isSkip) {
                LogUtils.info("执行时间:{} ms", millis);
            }
            return object;
        } catch (Exception e) {
            if(e instanceof WebException) {
                WebException e1 = (WebException) e;
                BaseResult baseResult = null;
                if(pjp.toString().startsWith("execution(Result")) {
                    baseResult = new Result(e1.getCode(), e1.getMessage());
                } else if(pjp.toString().startsWith("execution(PageResult")) {
                    baseResult = new PageResult(e1.getCode(), e1.getMessage());
                } else {
                    LogUtils.error(e.getMessage(), e);
                }
                LogUtils.error("返回参数:{}", JsonUtils.toJsonLogStr(baseResult, JsonUtils.getEncryption()));
                return baseResult;
            }
            throw e;
        } finally {
            MDC.remove("keep");
        }
    }

    /**
     * 前置通知
     * @param joinPoint
     */
//    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint){

    }

    /**
     * 后置通知
     * @param joinPoint
     */
//    @AfterReturning("controllerAspect()")
    public void doAfter(JoinPoint joinPoint){
//        System.out.println("后置通知");
    }

    /**
     * 最终通知
     * @param joinPoint
     */
//    @After("controllerAspect()")
    public void after(JoinPoint joinPoint){
//        System.out.println("最终通知");
    }

    /**
     * 例外通知
     * @param joinPoint
     */
//    @AfterThrowing(pointcut = "controllerAspect()", throwing="ex")
    public void doAfterThrow(JoinPoint joinPoint, Throwable ex){
        LogUtils.error(ex.getMessage(), ex);
    }

}
