package com.coconut.ds7.dto.output.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/12 17:11
 * File: UserOutputDto
 * Project: dS7
 */
@ApiModel(value = "用户信息返回DTO", description = "描述用户详细信息，包含姓名、密码等")
@Data
public class UserOutputDto implements Serializable {
    @ApiModelProperty(value = "ID")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
}
