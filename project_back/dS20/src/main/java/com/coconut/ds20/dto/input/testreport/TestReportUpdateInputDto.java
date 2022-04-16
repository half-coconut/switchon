package com.coconut.ds20.dto.input.testreport;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 22:18
 * File: TestReportUpdateInputDto
 * Project: dS20
 */

@Data
public class TestReportUpdateInputDto {
    @NotNull(message = "测试报告ID不能为空")
    private Integer id;
    private String result;
    @NotNull(message = "所属运行记录ID不能为空")
    private Integer testRecordId;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
}
