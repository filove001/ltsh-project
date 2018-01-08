package com.ltsh.common.utils;

import com.ltsh.common.util.Dates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Random on 2017/07/12.
 */
public class BeanUtils {
    private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
    public static void copyList(List sourceList, List targetList, Class targetClass) throws Exception {
        for (Object object: sourceList) {
            Object target = targetClass.newInstance();
            copyProperties(object, target);
            targetList.add(target);
        }
    }
    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, null, null);
    }
    public static void copyProperties2(Object source, Object target) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
    public static void copyProperties(Object source, Object target, String[] toStringProperties) {
        copyProperties(source, target, toStringProperties, null);
    }
    public static void copyProperties(Object source, Object target, String[] toStringProperties, Class<?> editable, String... ignoreProperties) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        if(editable != null) {
            if(!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }

            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = org.springframework.beans.BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = ignoreProperties != null? Arrays.asList(ignoreProperties):null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if(writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                boolean isToString = false;
                if(toStringProperties != null) {
                    for (String str : toStringProperties) {
                        if(str.equals(targetPd.getName())) {
                            isToString = true;
                            break;
                        }
                    }
                }
                PropertyDescriptor sourcePd = org.springframework.beans.BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if(sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if((readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) || isToString) {
                        try {
                            if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source, new Object[0]);
                            if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            if(isToString && value != null) {
                                if(value instanceof Date) {
                                    value = Dates.toStr((Date)value);
                                } else {
                                    value = value.toString();
                                }

                            }

                            writeMethod.invoke(target, new Object[]{value});
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }

    }
}
