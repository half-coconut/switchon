package com.coconut.ds20.dto.output;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:12
 * File: BaseOutputDto
 * Project: dS20
 */

@Data
public class BaseOutputDto implements Serializable {
    private Integer createById;
    private String createByName;
    private Date createTime;
    private Integer updateById;
    private String updateByName;
    private Date updateTime;
}
