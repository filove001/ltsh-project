package com.ltsh.chat.web.common.exception;

import com.ltsh.chat.service.enums.ResultCodeEnum;

/**
 * Created by Random on 2017/10/11.
 */
public class WebException extends Exception {
    private String code;
    private String message;

    public WebException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public WebException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public WebException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
