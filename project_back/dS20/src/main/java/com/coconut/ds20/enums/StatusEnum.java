package com.coconut.ds20.enums;

import lombok.Getter;

/**
 * 状态的枚举类
 */
@Getter
public enum StatusEnum {

    ENABLE(0, "启用"),
    DISABLE(1, "禁用"),
    DELETE(2, "删除"),
    ;

    StatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private final int status;
    private final String msg;

}
