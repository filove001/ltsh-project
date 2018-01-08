package com.ltsh.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/11.
 */
@Data
public class UserToken implements Serializable {
    private Integer id;
    private String loginName;//登录名
    private String name;//显示名
    private String phone;//手机号码
    private String token;


    public UserToken(Integer id, String loginName, String name, String phone, String token) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.phone = phone;
        this.token = token;
    }
}
