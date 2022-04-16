package com.coconut.ds20.dto.input.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/6 21:04
 * File: UserCreateInputDto
 * Project: dS20
 */

@Data
public class UserCreateInputDto {
    @NotNull(message = "用户名不能为空")
    @Length(min = 2, max = 10, message = "用户名长度必须在2-10个字符之间")
    private String username;
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
    private String password;
    @NotNull
    @Length(min = 2, max = 50, message = "姓名长度必须在2-50个字符之间")
    private String name;
    private String email;
    private String mobile;
    private List<String> roles;
    private String description;
    private Integer currentProjectId;
    private Date lastLoginTime;
}
