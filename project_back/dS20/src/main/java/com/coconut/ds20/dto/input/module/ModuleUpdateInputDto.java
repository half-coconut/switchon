package com.coconut.ds20.dto.input.module;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 23:00
 * File: ModuleUpdateInputDto
 * Project: dS20
 */

@Data
public class ModuleUpdateInputDto {
    @NotNull(message = "模块ID不能为空")
    private Integer id;
    @NotNull(message = "模块名称不能为空")
    private String name;
    private String description;
    private Integer status;
    @NotNull(message = "项目ID不能为空")
    private Integer projectId;
}
