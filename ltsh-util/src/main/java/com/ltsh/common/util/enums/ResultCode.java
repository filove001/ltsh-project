package com.ltsh.common.util.enums;


/**
 * Created by fengjianbo on 2018/3/8.
 */
public class ResultCode {

    public final static ResultCode CG = newR("000000", "成功");
    public final static ResultCode SB_FORMAT = newR("990001", "%s失败");

    private String code;
    private String message;

    public ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getFormatMessage(String ... pars) {
        return String.format(this.message, pars);
    }
    public static ResultCode newR(String code, String message) {
        return new ResultCode(code, message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
