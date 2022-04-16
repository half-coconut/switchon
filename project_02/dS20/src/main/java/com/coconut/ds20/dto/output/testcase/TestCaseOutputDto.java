package com.coconut.ds20.dto.output.testcase;

import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 16:21
 * File: TestCaseOutputDto
 * Project: dS20
 */

@Data
public class TestCaseOutputDto {
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
    private Integer testSuitId;
    private Integer taskId;
    private Integer projectId;
}
