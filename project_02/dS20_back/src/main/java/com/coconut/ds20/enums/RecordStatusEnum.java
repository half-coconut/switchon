package com.coconut.ds20.enums;

import lombok.Getter;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 23:15
 * File: RecordStatusEnum
 * Project: dS20
 */

/**
 * 运行记录的状态枚举
 */
@Getter
public enum RecordStatusEnum {

    FINISHED(0, "执行完成"),
    RUNNING(1, "执行中"),
    ;

    RecordStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private final int status;
    private final String msg;

}
