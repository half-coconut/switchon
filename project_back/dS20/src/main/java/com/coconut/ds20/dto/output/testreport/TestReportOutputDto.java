package com.coconut.ds20.dto.output.testreport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 22:17
 * File: TestReportOutputDto
 * Project: dS20
 */

@Data
public class TestReportOutputDto {
    private Integer id;
    private String result;
    private Integer status;
    private Integer testRecordId;
    private Integer projectId;
    private Long totalOfTestCase;
    private Long totalOfTestCaseForSuccess;
    private Long totalOfTestCaseForFailure;
    private Long totalOfTestCaseForError;
}
