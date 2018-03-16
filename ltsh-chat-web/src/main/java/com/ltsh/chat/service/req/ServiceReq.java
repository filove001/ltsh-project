package com.ltsh.chat.service.req;

import com.ltsh.util.beetsql.entity.req.BaseReq;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by fengjianbo on 2018/3/8.
 */
@Data
public class ServiceReq<T> extends BaseReq {
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

    private T content;

}
