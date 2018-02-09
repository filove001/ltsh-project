package com.ltsh.chat.service.utils;

import com.ltsh.common.util.security.Base64;
import com.ltsh.common.util.security.MD5Util;

/**
 * Created by Random on 2017/11/7.
 */
public class WebPasswordUtil {
    private final static String prefix1 = "ltsh:";
    private final static String prefix2 = "ltshChat:";
    private final static String prefix3 = "chat:";
    private final static String prefix4 = "ltshUser:";
    public static String createPassword(String password) {
        return createPassword(prefix3, password);
    }
    public static String createPassword(String prefix, String password) {

        return MD5Util.encoder(prefix + password);
    }
    public static boolean verify(String inputPassword,String dbPassword, String randomValue) {
        String encoder2 = MD5Util.encoder(MD5Util.encoder(MD5Util.encoder(prefix2 + dbPassword + randomValue)));
        String encoder1 = MD5Util.encoder(inputPassword);
        if(encoder1.equals(encoder2)) {
            return true;
        }
        return false;
    }

}
