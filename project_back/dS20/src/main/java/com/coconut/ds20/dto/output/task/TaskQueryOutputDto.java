package com.coconut.ds20.dto.output.task;

import com.coconut.ds20.dto.output.job.JobOutputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import lombok.Data;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 1:19
 * File: TaskQueryOutputDto
 * Project: dS20
 */

@Data
public class TaskQueryOutputDto {
    private Integer id;
    private String name;
    private String description;
    private Integer projectId;
    private Boolean isArchive;
    private Integer archiveId;
    private String archiveName;
    private Boolean isJob;

    private List<ModuleOutputDto> modules;
    private List<JobOutputDto> jobs;
}
