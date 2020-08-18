package com.dtinone.datashare.common.enums;

/**
 * 状态枚举
 */
public enum StatusEnum {
    WAIT_REGISTER("0", "待注册"),
    WAIT_AUDIT("1", "待审核"),
    WAIT_PUSH("2", "待发布"),
    ALREADY_PUSH("3", "已发布"),
    ALREADY_OFFLINE("4", "已下架");

    private String value;
    private String chnName;


    StatusEnum(String v, String chnName) {
        this.value = v;
        this.chnName = chnName;
    }

    public static StatusEnum nameOfValue(String value) {
        for (StatusEnum info : StatusEnum.values()) {
            if (info.getValue().equals(value)) {
                return info;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getChnName() {
        return chnName;
    }
}
