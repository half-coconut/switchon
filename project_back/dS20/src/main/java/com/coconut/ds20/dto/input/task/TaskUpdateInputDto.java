package com.coconut.ds20.dto.input.task;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 23:01
 * File: TaskUpdateInputDto
 * Project: dS20
 */

@Data
public class TaskUpdateInputDto {
    @NotNull(message = "任务ID不能为空")
    private Integer id;
    @NotNull(message = "任务名称不能为空")
    private String name;
    private String description;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
    @NotNull(message = "关联模块不能为空")
    private List<Integer> moduleIds;
}
