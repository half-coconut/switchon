package com.coconut.ds7.entity;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/4 18:37
 * File: VersionEntity
 * Project: dS7
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.lang.reflect.Type;

/**
 * 版本 实体类
 */
@Data
@TableName("version")
public class VersionEntity extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 版本名称
     */
    private String name;
    private String description;
}
