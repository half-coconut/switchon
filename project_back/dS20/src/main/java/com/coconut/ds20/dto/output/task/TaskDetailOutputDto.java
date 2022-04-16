package com.coconut.ds20.dto.output.task;

import com.coconut.ds20.dto.output.testsuite.TestSuiteDetailOutputDto;
import lombok.Data;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 17:00
 * File: TaskDetailOutputDto
 * Project: dS20
 */

@Data
public class TaskDetailOutputDto {
    private Integer id;
    private String name;
    private String description;
    private Integer projectId;
    private Boolean isArchive;
    private Integer archiveId;
    private String archiveName;
    private Boolean isJob;
    private List<TestSuiteDetailOutputDto> testSuits;
}
