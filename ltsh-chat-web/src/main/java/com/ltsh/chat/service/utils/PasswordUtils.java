package com.ltsh.chat.service.utils;

import com.ltsh.common.util.security.MD5Util;

/**
 * Created by Random on 2017/11/7.
 */
public class PasswordUtils {
    private final static String prefix1 = "ltsh:";
    private final static String prefix2 = "ltshChat:";
    private final static String prefix3 = "chat:";
    private final static String prefix4 = "ltshUser:";

    public static String createPassword(String password) {
        return MD5Util.encoder(prefix3+password);
    }
    public static boolean verify(String password,String inputPassword, String randomValue) {
        String encoder2 = MD5Util.encoder(prefix1 + MD5Util.encoder(prefix2 + inputPassword + randomValue));
        String encoder1 = MD5Util.encoder(prefix1 + password);
        if(encoder1.equals(encoder2)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String password = createPassword("hBoA0kF+mAexl5vSroKlpg==");
        System.out.println(password);
    }
}
