package org.ltsh.chat.service.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Random on 2017/10/23.
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 创建用户id
     */
    private Integer createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改用户
     */
    private Integer updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;
}
