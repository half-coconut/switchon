package com.coconut.ds7.dto.input.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/12 16:53
 * File: UserUpdateInputDto
 * Project: dS7
 */
@ApiModel(value = "用户修改传入DTO", description = "用户修改传入DTO")
@Data
public class UserUpdateInputDto implements Serializable {
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
