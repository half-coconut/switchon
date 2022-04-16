package com.coconut.ds20.dto.output.testrecord;

import com.coconut.ds20.dto.output.task.TaskOutputDto;
import com.coconut.ds20.dto.output.testreport.TestReportOutputDto;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 20:51
 * File: TestRecordQueryOutputDto
 * Project: dS20
 */

@Data
public class TestRecordQueryOutputDto {
    private Integer id;
    private String name;
    private Integer recordStatus;
    private Integer environmentId;
    private Integer taskId;
    private Integer projectId;

    private TaskOutputDto task;

    private TestReportOutputDto testReport;
}
