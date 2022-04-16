package com.coconut.ds20.dto.input.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 17:02
 * File: TaskRunInputDto
 * Project: dS20
 */

@Data
public class TaskRunInputDto {
    @NotNull(message = "测试任务ID不能为空")
    private Integer taskId;
    @NotNull(message = "项目ID不能为空")
    private Integer projectId;
    @NotNull(message = "项目环境ID不能为空")
    private Integer environmentId;
    private String name;
}
