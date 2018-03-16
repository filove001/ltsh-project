package com.ltsh.util.beetsql.entity.req;

import com.ltsh.util.beetsql.entity.UserToken;

import java.io.Serializable;

/**
 * Created by fengjianbo on 2018/2/11.
 */

public class BaseReq<T> implements Serializable {
    /**
     * 请求流水
     */
    private String keep;
    private String serviceName;
    private long timestamp;
    private UserToken userToken;
    private T content;

    public BaseReq() {
    }

    public BaseReq(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public UserToken getUserToken() {
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setKeep(String keep) {
        this.keep = keep;
    }

    public String getKeep() {
        return keep;
    }
}
