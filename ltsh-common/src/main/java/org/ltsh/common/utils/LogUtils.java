package org.ltsh.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * Created by Random on 2017/10/26.
 */
public class LogUtils {
    private static String[] getMethodNames(StackTraceElement[] sElements, int index){
        String[] strs = new String[3];
        strs[0] = sElements[index].getFileName();
        strs[1] = sElements[index].getMethodName();
        strs[2] = sElements[index].getLineNumber() + "";
        return strs;
    }

    private static String createLog(String log) {
        String[] methodNames = getMethodNames(new Throwable().getStackTrace(), 2);
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodNames[1]);
        buffer.append("(").append(methodNames[0]).append(":").append(methodNames[2]).append(")");
        buffer.append(log);
        return buffer.toString();
    }
    private static Logger getLogger() {
        String[] methodNames = getMethodNames(new Throwable().getStackTrace(), 1);
        Logger logger = LoggerFactory.getILoggerFactory().getLogger(methodNames[0]);
        return logger;
    }
    public static String getName() {
        return getLogger().getName();
    }



    public static void trace(String s) {
        getLogger().trace(createLog(s));
    }

    public static void trace(String s, Object o) {
        getLogger().trace(createLog(s), o);
    }

    public static void trace(String s, Object o, Object o1) {
        getLogger().trace(createLog(s), o, o1);
    }

    public static void trace(String s, Object... objects) {
        getLogger().trace(createLog(s), objects);
    }

    public static void trace(String s, Throwable throwable) {
        getLogger().trace(createLog(s), throwable);
    }

    public static boolean isTraceEnabled(Marker marker) {
        return getLogger().isTraceEnabled(marker);
    }

    public static void trace(Marker marker, String s) {
        getLogger().trace(marker, createLog(s));
    }

    public static void trace(Marker marker, String s, Object o) {
        getLogger().trace(marker, createLog(s), o);
    }

    public static void trace(Marker marker, String s, Object o, Object o1) {
        getLogger().trace(marker, createLog(s), o, o1);
    }

    public static void trace(Marker marker, String s, Object... objects) {
        getLogger().trace(marker, createLog(s), objects);
    }

    public static void trace(Marker marker, String s, Throwable throwable) {
        getLogger().trace(marker, createLog(s), throwable);
    }

    public static boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }

    public static void debug(String s) {
        getLogger().debug(createLog(s));
    }

    public static void debug(String s, Object o) {
        getLogger().debug(createLog(s), o);
    }

    public static void debug(String s, Object o, Object o1) {
        getLogger().debug(createLog(s), o, o1);
    }

    public static void debug(String s, Object... objects) {
        getLogger().debug(createLog(s), objects);
    }

    public static void debug(String s, Throwable throwable) {
        getLogger().debug(createLog(s), throwable);
    }

    public static boolean isDebugEnabled(Marker marker) {
        return getLogger().isDebugEnabled(marker);
    }

    public static void debug(Marker marker, String s) {
        getLogger().debug(marker,createLog(s));
    }

    public static void debug(Marker marker, String s, Object o) {
        getLogger().debug(marker,createLog(s), o);
    }

    public static void debug(Marker marker, String s, Object o, Object o1) {
        getLogger().debug(marker,createLog(s), o, o1);
    }

    public static void debug(Marker marker, String s, Object... objects) {
        getLogger().debug(marker,createLog(s), objects);
    }

    public static void debug(Marker marker, String s, Throwable throwable) {
        getLogger().debug(marker,createLog(s), throwable);
    }

    public static boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }

    public static void info(String s){
        getLogger().info(createLog(s));
    }

    public static void info(String s, Object o) {
        getLogger().info(createLog(s), o);
    }

    public static void info(String s, Object o, Object o1) {
        getLogger().info(createLog(s), o, o1);
    }

    
    public static void info(String s, Object... objects) {
        getLogger().info(createLog(s), objects);
    }

    
    public static void info(String s, Throwable throwable) {
        getLogger().info(createLog(s), throwable);
    }

    
    public static boolean isInfoEnabled(Marker marker) {
        return getLogger().isInfoEnabled(marker);
    }

    
    public static void info(Marker marker, String s) {
        getLogger().info(marker, createLog(s));
    }

    
    public static void info(Marker marker, String s, Object o) {
        getLogger().info(marker, createLog(s), o);
    }

    
    public static void info(Marker marker, String s, Object o, Object o1) {
        getLogger().info(marker, createLog(s), o, o1);
    }

    
    public static void info(Marker marker, String s, Object... objects) {
        getLogger().info(marker, createLog(s), objects);
    }

    
    public static void info(Marker marker, String s, Throwable throwable) {
        getLogger().info(marker, createLog(s), throwable);
    }

    
    public static boolean isWarnEnabled() {
        return getLogger().isWarnEnabled();
    }

    
    public static void warn(String s) {
        getLogger().warn(createLog(s));
    }

    
    public static void warn(String s, Object o) {
        getLogger().warn(createLog(s), o);
    }

    
    public static void warn(String s, Object... objects) {
        getLogger().warn(createLog(s), objects);
    }

    
    public static void warn(String s, Object o, Object o1) {
        getLogger().warn(createLog(s), o, o1);
    }

    
    public static void warn(String s, Throwable throwable) {
        getLogger().warn(createLog(s), throwable);
    }

    
    public boolean isWarnEnabled(Marker marker) {
        return getLogger().isWarnEnabled(marker);
    }

    
    public static void warn(Marker marker, String s) {
        getLogger().warn(marker,createLog(s));
    }

    
    public static void warn(Marker marker, String s, Object o) {
        getLogger().warn(marker,createLog(s), o);
    }

    
    public static void warn(Marker marker, String s, Object o, Object o1) {
        getLogger().warn(marker,createLog(s), o, o1);
    }

    
    public static void warn(Marker marker, String s, Object... objects) {
        getLogger().warn(marker,createLog(s), objects);
    }

    
    public static void warn(Marker marker, String s, Throwable throwable) {
        getLogger().warn(marker,createLog(s), throwable);
    }

    
    public boolean isErrorEnabled() {
        return getLogger().isErrorEnabled();
    }

    
    public static void error(String s) {
        getLogger().error(createLog(s));
    }

    
    public static void error(String s, Object o) {
        getLogger().error(createLog(s), o);
    }

    
    public static void error(String s, Object o, Object o1) {
        getLogger().error(createLog(s), o, o1);
    }

    
    public static void error(String s, Object... objects) {
        getLogger().error(createLog(s), objects);
    }

    
    public static void error(String s, Throwable throwable) {
        getLogger().error(createLog(s), throwable);
    }

    
    public boolean isErrorEnabled(Marker marker) {
        return getLogger().isErrorEnabled(marker);
    }

    
    public static void error(Marker marker, String s) {
        getLogger().error(marker, createLog(s));
    }

    
    public static void error(Marker marker, String s, Object o) {
        getLogger().error(marker, createLog(s), o);
    }

    
    public static void error(Marker marker, String s, Object o, Object o1) {
        getLogger().error(marker, createLog(s), o, o1);
    }

    
    public static void error(Marker marker, String s, Object... objects) {
        getLogger().error(marker, createLog(s), objects);
    }

    
    public static void error(Marker marker, String s, Throwable throwable) {
        getLogger().error(marker, createLog(s), throwable);
    }

}
