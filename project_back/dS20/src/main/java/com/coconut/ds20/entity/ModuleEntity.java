package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 22:55
 * File: ModuleEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_module",autoResultMap = true)
public class ModuleEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Integer projectId;
}
