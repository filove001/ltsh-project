package com.ltsh.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class BaseResult<T> implements Serializable {
    private String code;
    private String message;
    private T content;

    public BaseResult(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public BaseResult(String code, String message, T content) {
        this.code = code;
        this.message = message;
        this.content = content;
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

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
