package com.coconut.ds20.dto.input.environment;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 0:52
 * File: EnvironmentUpdateInputDto
 * Project: dS20
 */

@Data
public class EnvironmentUpdateInputDto {
    @NotNull(message = "环境ID不能为空")
    private Integer id;
    @NotNull(message = "环境名称不能为空")
    private String name;
    @NotNull(message = "主机不能为空")
    private String host;
    @NotNull(message = "数据库配置不能为空")
    private String dbConfig;
    private String description;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
}
