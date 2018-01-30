package com.ltsh.chat.service.resp;

import com.ltsh.chat.service.enums.ResultCodeEnum;
import com.ltsh.common.entity.BaseResult;
import lombok.Data;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class Result<T> extends BaseResult {
    private T content;
    public Result(){
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }
    public Result(String code, String message) {
        super(code, message);
    }
    public Result(ResultCodeEnum resultCode) {
        super(resultCode.getCode(), resultCode.getMessage());
    }
    public Result(ResultCodeEnum resultCode, String message) {
        super(resultCode.getCode(), String.format(resultCode.getMessage(), message));
    }
    public Result(T content) {
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
