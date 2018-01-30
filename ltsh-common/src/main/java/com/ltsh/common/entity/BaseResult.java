package com.ltsh.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class BaseResult implements Serializable {
    private String code;
    private String message;


    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSucceed() {
        if("000000".equals(this.code)) {
            return true;
        }
        return false;
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
