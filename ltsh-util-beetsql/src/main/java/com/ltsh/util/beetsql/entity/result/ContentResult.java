package com.ltsh.util.beetsql.entity.result;

import com.ltsh.common.util.enums.ResultCode;


/**
 * Created by fengjianbo on 2018/3/8.
 */
public class ContentResult<T> extends LtshResult {

    private T content;
    public ContentResult(ResultCode resultCode) {
        super(resultCode.getCode(), resultCode.getMessage());
    }

    public ContentResult(ResultCode resultCode, T content) {
        super(resultCode.getCode(), resultCode.getMessage());
        this.content = content;
    }
//    public ContentResult(T content) {
//        super(ResultCode.CG.getCode(), ResultCode.CG.getMessage());
//        this.content = content;
//    }
    public ContentResult(String code, String message) {
        super(code, message);
    }
    public ContentResult(String code, String message, T content) {
        super(code, message);
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public boolean chengGong() {
        if(ResultCode.CG.getCode().equals(getCode())) {
            return true;
        }
        return false;
    }
}
