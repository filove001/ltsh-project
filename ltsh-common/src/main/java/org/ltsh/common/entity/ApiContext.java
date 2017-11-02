package org.ltsh.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Random on 2017/10/9.
 */
@Data
public class ApiContext implements Serializable {
    /**
     * 请求流水
     */
    private String keep;

}
