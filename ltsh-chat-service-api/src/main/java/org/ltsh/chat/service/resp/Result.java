package org.ltsh.chat.service.resp;

import lombok.Data;
import org.ltsh.chat.service.enums.ResultCodeEnum;
import org.ltsh.common.entity.BaseResult;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class Result<T> extends BaseResult<T> {
    public Result(){
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }
    public Result(String code, String message) {
        super(code, message);
    }
    public Result(ResultCodeEnum resultCode) {
        super(resultCode.getCode(), resultCode.getMessage());
    }
    public Result(T content) {
        super(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), content);
    }
}
