package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:00
 * File: ProjectEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_project", autoResultMap = true)
public class ProjectEntity extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Integer ownerId;
    private String ownerName;
}
