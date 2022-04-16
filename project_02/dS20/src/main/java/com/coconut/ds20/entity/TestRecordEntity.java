package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 18:47
 * File: TestRecordEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_test_record",autoResultMap = true)
public class TestRecordEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer threadCount;
    private String description;
    private Integer recordStatus;
    private Boolean isDelete;
    private Integer environmentId;
    private Integer taskId;
    private Integer projectId;
}
