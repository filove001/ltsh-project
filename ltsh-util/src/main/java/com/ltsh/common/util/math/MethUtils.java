package com.ltsh.common.util.math;

import java.math.BigDecimal;

/**
 * Created by Random on 2017/5/25.
 */
public class MethUtils {
    /**
     * 分转元(保留两位小数)
     * @return
     */
    public static String fenToYuan(Long fen){
        BigDecimal bigDecimal = new BigDecimal(fen);
        bigDecimal = bigDecimal.divide(new BigDecimal(100));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }
    /**
     * 分转元(保留两位小数)
     * @return
     */
    public static String fenToYuan(String fen){
        BigDecimal bigDecimal = new BigDecimal(fen);
        bigDecimal = bigDecimal.divide(new BigDecimal(100));
        bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.toString();
    }
    /**
     * 元转分(转换为long,没有小数)
     * @return
     */
    public static Long yuanToFen(Double yuan){
        BigDecimal bigDecimal = new BigDecimal(yuan);
        bigDecimal = bigDecimal.multiply(new BigDecimal(100));
        return bigDecimal.longValue();
    }
    /**
     * 元转分(转换为long,没有小数)
     * @return
     */
    public static String yuanToFen(String yuan){
        BigDecimal bigDecimal = new BigDecimal(yuan);
        bigDecimal = bigDecimal.multiply(new BigDecimal(100));
        return String.valueOf(bigDecimal.longValue());
    }
    public static void main(String[] args) {
        String s = fenToYuan(102399L);
        Long aLong = yuanToFen(23.9956);
        System.out.println(s);
        System.out.println(aLong);
    }
}
