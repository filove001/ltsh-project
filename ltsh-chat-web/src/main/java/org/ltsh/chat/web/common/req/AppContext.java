package org.ltsh.chat.web.common.req;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.ltsh.common.entity.UserToken;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class AppContext implements Serializable {
    /**
     * 设备媒体uuid
     */
    @NotEmpty
    private String medium;
    /**
     * 设备媒体类型 0:内部通讯,1:安卓,2:IOS,3:web端
     */
    @NotEmpty
    private String mediumType;
    /**
     * 系统版本
     */
    private String sysVersion;
    /**
     * 手机型号
     */
    private String phoneModel;

    /**
     * app版本号
     */
    @NotEmpty
    private String appVersion;
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
     * 请求流水
     */
    @NotEmpty
    private String keep;
    /**
     * 登录token
     */
    private String token;
    /**
     * 用户token
     */
    private UserToken userToken;
    /**
     * 应用id
     */
    @NotEmpty
    private String appId;
    /**
     * 随机数key
     */
    private String randomKey;
}
