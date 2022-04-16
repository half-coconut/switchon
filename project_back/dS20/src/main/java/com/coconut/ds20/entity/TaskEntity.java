package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 16:24
 * File: TaskEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_task",autoResultMap = true)
public class TaskEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Boolean isArchive;
    private Boolean isJob;
    private Integer projectId;
    private Integer archiveId;
    private String archiveName;
}
