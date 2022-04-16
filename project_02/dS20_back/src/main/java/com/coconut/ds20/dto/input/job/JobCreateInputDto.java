package com.coconut.ds20.dto.input.job;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 17:16
 * File: JobCreateInputDto
 * Project: dS20
 */

@Data
public class JobCreateInputDto {
    @NotNull(message = "定时执行名称不能为空")
    private String name;
    @NotNull(message = "CRON表达式不能为空")
    private String cron;
    private String description;
    @NotNull(message = "所属任务ID不能为空")
    private Integer taskId;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
    @NotNull(message = "所属项目环境ID不能为空")
    private Integer environmentId;
}
