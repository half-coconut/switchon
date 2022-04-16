package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 16:26
 * File: JobEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_job",autoResultMap = true)
public class JobEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String cron;
    private Integer status;
    private Integer taskId;
    private Integer projectId;
    private Integer environmentId;
    private Integer xxlJobId;
    private Boolean isDelete;
    private String description;
}
