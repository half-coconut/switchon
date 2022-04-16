package com.coconut.ds20.dto.output.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.coconut.ds20.dto.output.BaseOutputDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/6 21:06
 * File: UserOutputDto
 * Project: dS20
 */

@Data
public class UserOutputDto extends BaseOutputDto {
    private Integer id;
    private String username;
    private String name;
    private String email;
    private String mobile;
    private List<String> roles;
    private Integer status;
    private String description;
    private Integer current_project_id;
    private Date lastLoginTime;
}
