package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/9 11:46
 * File: TaskModuleEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_task_module",autoResultMap = true)
public class TaskModuleEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer taskId;
    private Integer moduleId;
}
