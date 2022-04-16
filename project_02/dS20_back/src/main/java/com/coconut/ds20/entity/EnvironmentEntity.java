package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 0:44
 * File: EnvironmentEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_environment")
public class EnvironmentEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String host;
    private String dbConfig;
    private String description;
    private Integer status;
    private Integer projectId;
}
