package com.ltsh.common.client.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by Random on 2017/5/11.
 */
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取applicationContext对象
     * @return
     */
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    /**
     * 根据bean的id来查找对象
     * @param id
     * @return
     */
    public static Object getBean(String id){
        return applicationContext.getBean(id);
    }

    /**
     * 根据类型获取对象
     * @param var1
     * @param <T>
     * @return
     */
    public static  <T> T getBean(Class<T> var1) {
        return applicationContext.getBean(var1);
    }


}
