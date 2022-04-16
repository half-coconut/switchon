package com.coconut.ds20.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/6 20:01
 * File: UserEntity
 * Project: dS20
 */

@Data
@TableName(value = "cc_user",autoResultMap = true)
public class UserEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String mobile;
    @TableField(jdbcType = JdbcType.VARCHAR,typeHandler = ListTypeHandler.class)
    private List<String> roles;
    private Integer status;
    private String description;
    private Integer currentProjectId;
    private Date lastLoginTime;

}
