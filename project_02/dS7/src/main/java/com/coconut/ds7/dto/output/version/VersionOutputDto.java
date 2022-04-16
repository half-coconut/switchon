package com.coconut.ds7.dto.output.version;

import com.coconut.ds7.dto.output.BaseOutputDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:54
 * File: VersionOutputDto
 * Project: dS7
 */

/**
 * 版本返回 DTO
 */
@ApiModel(value = "版本信息返回DTO", description = "描述版本详细信息")
@Data
public class VersionOutputDto extends BaseOutputDto {
    @ApiModelProperty(value = "ID", example = "1")
    private Integer id;
    @ApiModelProperty(value = "版本名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
}
