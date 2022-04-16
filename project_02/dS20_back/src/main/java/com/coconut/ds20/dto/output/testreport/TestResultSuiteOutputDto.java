package com.coconut.ds20.dto.output.testreport;

import lombok.Data;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 0:37
 * File: TestResultSuiteOutputDto
 * Project: dS20
 */

@Data
public class TestResultSuiteOutputDto {
    private Integer testSuitId;
    private String testSuitName;

    private List<TestResultCaseOutputDto> testCaseResults;
}
