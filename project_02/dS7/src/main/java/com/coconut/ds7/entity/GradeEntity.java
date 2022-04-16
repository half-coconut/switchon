package com.coconut.ds7.entity;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/27 22:16
 * File: GradeEntity
 * Project: dS7
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 等级实体
 * 1.@Data 是lombok 里，不需要写get和set了
 * 2.继承于 Serializable，可以只支持序列化和反序列化
 */
@Data
@TableName("grade")
public class GradeEntity extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
}
