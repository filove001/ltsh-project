package com.ltsh.util.beetsql.entity.result;

import java.io.Serializable;

/**
 * Created by fengjianbo on 2018/3/8.
 */
public class LtshResult implements Serializable {
    private String code;
    private String message;

    public LtshResult(String code, String message) {
        this.code = code;
        this.message = message;
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
