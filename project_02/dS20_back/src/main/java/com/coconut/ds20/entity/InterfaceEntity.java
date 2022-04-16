package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 22:16
 * File: InterfaceEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_interface", autoResultMap = true)
public class InterfaceEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String path;
    private String requestMethod;
    private String responseType;
    private String description;
    private Integer status;
    private Integer developerId;
    private String developerName;
    private Integer projectId;
    private Integer moduleId;
}
