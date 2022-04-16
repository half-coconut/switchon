package com.coconut.ds20.dto.input.project;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:11
 * File: ProjectCreateInputDto
 * Project: dS20
 */

@Data
public class ProjectCreateInputDto {
    @NotNull(message = "项目名称不能为空")
    @Length(min = 2, max = 20, message = "用户名长度必须在2-20个字符之间")
    private String name;
    private String description;
    private Integer ownerId;
    private String ownerName;
}
