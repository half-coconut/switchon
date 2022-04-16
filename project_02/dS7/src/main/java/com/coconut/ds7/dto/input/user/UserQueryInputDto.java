package com.coconut.ds7.dto.input.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/12 16:52
 * File: UserQueryInputDto
 * Project: dS7
 */
@ApiModel(value = "用户查询传入DTO", description = "用户查询传入DTO")
@Data
public class UserQueryInputDto implements Serializable {
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "姓名")
    private String name;
}
