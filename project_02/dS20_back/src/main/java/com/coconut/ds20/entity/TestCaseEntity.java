package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.yaml.snakeyaml.events.Event;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 16:14
 * File: TestCaseEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_test_case",autoResultMap = true)
public class TestCaseEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String requestData;
    private String extract;
    private String assertion;
    private String dbAssertion;
    private String marks;
    private String description;
    private Integer orderIndex;
    private Integer status;
    private Integer interfaceId;
    private Integer moduleId;
    private Integer testSuiteId;
    private Integer taskId;
    private Integer projectId;
}
