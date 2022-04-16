package com.coconut.ds20.dto.input.testrecord;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 20:44
 * File: TestRecordUpdateInputDto
 * Project: dS20
 */

@Data
public class TestRecordUpdateInputDto {
    @NotNull(message = "运行记录ID不能为空")
    private Integer id;
    @NotNull(message = "运行记录名称不能为空")
    private String name;
    @NotNull(message = "运行记录状态不能为空")
    private Integer recordStatus;
    @NotNull(message = "所属环境ID不能为空")
    private Integer environmentId;
    @NotNull(message = "所属任务ID不能为空")
    private Integer taskId;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
}
