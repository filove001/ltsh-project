package org.ltsh.chat.web.common.annotation;



import org.ltsh.chat.web.common.enums.CheckLoginType;

import java.lang.annotation.*;

/**
 * @描述：   检查是否登录注解 .
 * @作者：    Kunkka .
 * @创建时间：    2017年4月25日 上午9:18:51 .
 * @版本：    V1.0 .
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface CheckLogin {
    CheckLoginType value() default CheckLoginType.CHECK;
}

