package com.coconut.ds20.dto.input.testsuite;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 11:42
 * File: TestSuiteUpdateInputDto
 * Project: dS20
 */

@Data
public class TestSuiteUpdateInputDto {
    @NotNull(message = "测试套件ID不能为空")
    private Integer id;
    @NotNull(message = "测试套件名称不能为空")
    private String name;
    private String description;
    @NotNull(message = "所属项目不能为空")
    private Integer projectId;
    @NotNull(message = "所属任务不能为空")
    private Integer taskId;
}
