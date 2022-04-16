package com.coconut.ds7.dto.input.version;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:28
 * File: VersionCreateInputDto
 * Project: dS7
 */

/**
 * 版本创建传入 DTO
 */
@ApiModel(value = "版本创建传入 DTO", description = "版本传入信息DTO")
@Data
public class VersionCreateInputDto {
    @ApiModelProperty(value = "版本名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
}
