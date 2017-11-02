package org.ltsh.chat.service.enums;

/**
 * Created by Random on 2017/10/23.
 */
public enum SendTypeEnums {
    SX(0, "私信"),
    DY(1, "订阅"),
    XT(2,"系统");


    /** 枚举值 */
    private Integer value;
    /** 描述 */
    private String desc;
    SendTypeEnums(Integer value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
