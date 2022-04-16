package com.coconut.ds7.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/7 20:05
 * File: UserEntity
 * Project: dS7
 */

/**
 * 用户信息
 */
@Data
@TableName("user")
public class UserEntity extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private String description;
}
