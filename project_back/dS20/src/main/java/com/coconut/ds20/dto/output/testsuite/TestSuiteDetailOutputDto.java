package com.coconut.ds20.dto.output.testsuite;

import com.coconut.ds20.dto.output.BaseOutputDto;
import com.coconut.ds20.dto.output.testcase.TestCaseOutputDto;
import lombok.Data;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 11:45
 * File: TestSuiteDetailOutputDto
 * Project: dS20
 */

@Data
public class TestSuiteDetailOutputDto extends BaseOutputDto {
    private Integer id;
    private String name;
    private Integer projectId;
    private Integer taskId;
    private List<TestCaseOutputDto> testCases;
}
