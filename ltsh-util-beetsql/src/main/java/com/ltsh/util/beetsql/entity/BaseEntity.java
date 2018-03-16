package com.ltsh.util.beetsql.entity;



import java.io.Serializable;
import java.util.Date;

/**
 * Created by fengjianbo on 2018/3/6.
 */

public class BaseEntity implements Serializable {
    private Integer id;

    private Integer createBy;//创建用户
    private Date createTime;
    private Integer updateBy;
    private Date updateTime;


    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
