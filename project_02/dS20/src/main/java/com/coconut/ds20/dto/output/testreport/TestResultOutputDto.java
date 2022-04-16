package com.coconut.ds20.dto.output.testreport;

import com.coconut.ds20.dto.output.environment.EnvironmentOutputDto;
import lombok.Data;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 0:36
 * File: TestResultOutputDto
 * Project: dS20
 */

@Data
public class TestResultOutputDto {
    private Integer taskId;
    private String taskName;
    private Double totalDuration;
    private Integer status;
    private Long totalOfTestSuit;
    private Long totalOfTestCase;
    private Long totalOfTestCaseForSuccess;
    private Long totalOfTestCaseForFailure;
    private Long totalOfTestCaseForError;

    private List<TestResultSuiteOutputDto> testSuiteResults;
    private EnvironmentOutputDto environment;
}
