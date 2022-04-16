package com.coconut.ds7.dto.input.version;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:29
 * File: VersionQueryInputDto
 * Project: dS7
 */

/**
 * 版本查询传入 DTO
 */
@ApiModel(value = "版本查询传入参数 DTO", description = "版本查询传入参数DTO")
@Data
public class VersionQueryInputDto implements Serializable {
    @ApiModelProperty(value = "版本名称")
    private String name;
}
