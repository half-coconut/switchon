package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 22:11
 * File: TestReportEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_test_report",autoResultMap = true)
public class TestReportEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String result;
    private String description;
    private Integer status;
    private Integer testRecordId;
    private Integer projectId;
}
