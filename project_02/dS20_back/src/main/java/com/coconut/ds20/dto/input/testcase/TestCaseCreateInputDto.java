package com.coconut.ds20.dto.input.testcase;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 16:22
 * File: TestCaseCreateInputDto
 * Project: dS20
 */

@Data
public class TestCaseCreateInputDto {
    @NotNull(message = "测试用例名称不能为空")
    private String name;
    private String requestData;
    private String extract;
    private String assertion;
    private String dbAssertion;
    private String marks;
    private String description;
    private Integer orderIndex;
    @NotNull(message = "所属接口不能为空")
    private Integer interfaceId;
    @NotNull(message = "所属模块不能为空")
    private Integer moduleId;
    @NotNull(message = "所属测试套件不能为空")
    private Integer testSuiteId;
    @NotNull(message = "所属任务不能为空")
    private Integer taskId;
    @NotNull(message = "所属项目不能为空")
    private Integer projectId;
}
