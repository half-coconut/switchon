package com.coconut.ds7.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/31 15:25
 * File: BaseEntity
 * Project: dS7
 */

@Data
public class BaseEntity implements Serializable {
    private Integer createBy;
    private Integer updateBy;
    private Date createTime;
    private Date updateTime;
}
