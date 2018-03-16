package com.ltsh.common.util.enums;

/**
 * Created by Random on 2017/10/11.
 */
public enum CheckLoginType {
    CHECK("检查", 100),
    NOCHECK("不检查", 200);

    /** 描述 */
    private String desc;
    /** 枚举值 */
    private int value;

    private CheckLoginType(String desc, int value) {
        this.desc = desc;
        this.value = value;
    }
}
