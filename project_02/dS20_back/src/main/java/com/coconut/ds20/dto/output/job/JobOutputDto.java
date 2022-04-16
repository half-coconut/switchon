package com.coconut.ds20.dto.output.job;

import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 1:30
 * File: JobOutputDto
 * Project: dS20
 */

@Data
public class JobOutputDto {
    private Integer id;
    private String name;
    private String cron;
    private Integer status;
    private String description;
    private Integer taskId;
    private Integer projectId;
    private Integer environmentId;
    private Integer xxlJobId;
}
