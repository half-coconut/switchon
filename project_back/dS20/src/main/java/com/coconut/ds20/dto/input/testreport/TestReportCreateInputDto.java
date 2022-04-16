package com.coconut.ds20.dto.input.testreport;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 22:18
 * File: TestReportCreateInputDto
 * Project: dS20
 */

@Data
public class TestReportCreateInputDto {
    private String result;
    @NotNull(message = "所属运行记录ID不能为空")
    private Integer testRecordId;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
}
