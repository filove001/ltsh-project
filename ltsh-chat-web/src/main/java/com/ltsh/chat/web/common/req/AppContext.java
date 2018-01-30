package com.ltsh.chat.web.common.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import com.ltsh.common.entity.UserToken;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class AppContext implements Serializable {

    /**
     * app版本
     */
    private String appVersion;
    /**
     * 设备id
     */
    @NotEmpty
    private String medium;
    /**
     * appId
     */
    @NotEmpty
    private String appId;
    /**
     * 设备类型,0:内部通讯,1:安卓,2:IOS,3:web端
     */
    @NotEmpty
    private String mediumType;
    /**
     * 系统版本
     */
    private String systemVersion;
    /**
     * 手机厂商
     */
    private String deviceBrand;
    /**
     * 手机型号
     */
    private String systemModel;

    /**
     * 系统语言
     */
    private String systemLanguage;
    /**
     * 请求流水
     */
    @NotEmpty
    private String keep;
    /**
     * 时间戳字符串
     */
    @NotEmpty
    private String timestamp;
    /**
     * 签名信息
     */
    @NotEmpty
    private String signInfo;
    /**
     * 登录token
     */
    private String token;
    /**
     * 用户token
     */
    private UserToken userToken;

    /**
     * 随机数key
     */
    private String randomKey;
}
