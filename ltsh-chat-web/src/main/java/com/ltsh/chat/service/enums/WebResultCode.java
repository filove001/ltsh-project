package com.ltsh.chat.service.enums;

import com.ltsh.common.util.enums.ResultCode;

/**
 * Created by fengjianbo on 2018/3/8.
 */
public class WebResultCode extends ResultCode {
    public static final WebResultCode CF_FORMAT = newR("990002", "%s重复");
    public static final WebResultCode YZ_QM_SB = newR("990002", "验签失败");
    public static final WebResultCode YZ_CS_SB = newR("990003", "参数校验失败");
    public static final WebResultCode TOKEN_SX = newR("990004", "token已失效");
    public static final WebResultCode YZ_ZH_SB = newR("990005", "用户名或密码不正确");
    public WebResultCode(String code, String message) {
        super(code, message);
    }
    public static WebResultCode newR(String code, String message) {
        return new WebResultCode(code, message);
    }
}
