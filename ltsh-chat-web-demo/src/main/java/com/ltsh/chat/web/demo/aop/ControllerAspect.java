package com.ltsh.chat.web.demo.aop;


import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import com.ltsh.common.util.StringUtils;
import com.ltsh.common.util.security.SignUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.joda.time.Duration;
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

    @Pointcut("execution(* com.ltsh.chat.web.demo.controller.*.*(..))")
    private void controllerAspect(){}//定义一个切入点

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
                        list.add(obj);
                    }

                }
            }
            MDC.put("keep", keep);

            boolean isSkip = false;

            if(pjp.getSignature().getName().equals("getMessage") || pjp.getSignature().getName().equals("getRandomStr")) {
//                isSkip = true;
            }
            LogUtils.info("请求方法:{}.{},请求参数:{}",
                    pjp.getTarget().getClass().getName(),
                    pjp.getSignature().getName(),
                    JsonUtils.toJsonLogStr(list, JsonUtils.getEncryption()));

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

            throw e;
        } finally {
            MDC.remove("keep");
        }
    }


}
