package com.coconut.ds20.enums;

import lombok.Getter;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 18:06
 * File: JobStatusEnu
 * Project: dS20
 */

/**
 * 定时任务状态枚举
 */
@Getter
public enum JobStatusEnum {

    START(1, "开始"),
    STOP(2, "停止"),
    CREATE(3, "已创建"),
            ;

    JobStatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private final int status;
    private final String msg;
}
