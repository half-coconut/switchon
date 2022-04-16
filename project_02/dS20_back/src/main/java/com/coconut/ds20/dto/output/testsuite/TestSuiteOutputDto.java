package com.coconut.ds20.dto.output.testsuite;

import com.coconut.ds20.dto.output.BaseOutputDto;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 11:15
 * File: TestSuiteOutputDto
 * Project: dS20
 */

@Data
public class TestSuiteOutputDto extends BaseOutputDto {
    private Integer id;
    private String name;
    private Integer projectId;
    private Integer taskId;
}
