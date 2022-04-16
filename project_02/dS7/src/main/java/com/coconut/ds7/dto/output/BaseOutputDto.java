package com.coconut.ds7.dto.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:56
 * File: BaseOutputDto
 * Project: dS7
 */

/**
 * 返回信息 DTO 实体基类
 */
@Data
public class BaseOutputDto implements Serializable {
    @ApiModelProperty(value = "创建人")
    private Integer createBy;
    @ApiModelProperty(value = "创建时间")
    private Integer updateBy;
    @ApiModelProperty(value = "修改人")
    private Date createTime;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}
