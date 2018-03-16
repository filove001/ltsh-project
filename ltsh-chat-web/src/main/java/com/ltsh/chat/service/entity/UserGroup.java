package com.ltsh.chat.service.entity;

import com.ltsh.util.beetsql.entity.BaseEntity;
import lombok.Data;

/**
 * Created by fengjianbo on 2018-01-03 17:36:59.
 */
@Data
public class UserGroup extends BaseEntity {
    
        /**
         * 名称
         **/
        private String name;
        
        /**
         * 类型
         **/
        private Integer type;
        
        /**
         * 状态
         **/
        private String status;
        
        /**
         * 所有者
         **/
        private Integer owner;
        
        /**
         * 有效期
         **/
        private java.util.Date validity;
        
        /**
         * 级别类型
         **/
        private Integer levelType;
    
}
