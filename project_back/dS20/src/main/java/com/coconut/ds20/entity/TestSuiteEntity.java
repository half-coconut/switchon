package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 11:08
 * File: TestSuiteEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_test_suite",autoResultMap = true)
public class TestSuiteEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Integer projectId;
    private Integer taskId;
}
