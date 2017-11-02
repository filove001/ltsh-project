package org.ltsh.chat.service.enums;

/**
 * Created by Random on 2017/10/9.
 */
public enum ResultCodeEnum {
    SUCCESS("000000", "成功"),
    FAIL("990001", "%s失败"),
    SIGN_FAIL("990002", "验签失败"),
    PARAM_CHACK_FAIL("990003", "参数校验失败"),
    TOKEN_FAIL("990004", "token已失效"),
    LOGIN_FAIL("990005", "用户名或密码不正确");
    ResultCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

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
    public String getFormatMessage(String ... strs) {
        return String.format(this.getMessage(), strs);
    }
    public static ResultCodeEnum getEnumByCode(Integer code) {
        ResultCodeEnum resultCodeEnum = null;
        for (ResultCodeEnum tmpResultCodeEnum : values()) {
            if(code != null && tmpResultCodeEnum.getCode().equals(code)) {
                resultCodeEnum = tmpResultCodeEnum;
                break;
            }
        }
        return resultCodeEnum;
    }
}
