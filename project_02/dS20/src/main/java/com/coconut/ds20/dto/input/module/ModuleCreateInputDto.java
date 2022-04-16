package com.coconut.ds20.dto.input.module;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 22:59
 * File: ModuleCreateInputDto
 * Project: dS20
 */

@Data
public class ModuleCreateInputDto {
    @NotNull(message = "模块名称不能为空")
    private String name;
    private String description;
    @NotNull(message = "项目ID不能为空")
    private Integer projectId;
}
