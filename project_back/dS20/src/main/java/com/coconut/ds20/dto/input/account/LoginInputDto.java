package com.coconut.ds20.dto.input.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/14 14:58
 * File: LoginInputDto
 * Project: dS20
 */

@Data
public class LoginInputDto {
    @NotNull(message = "用户名不能为空")
    @Length(min = 2, max = 10, message = "用户名长度必须在2-10个字符之间")
    private String username;
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
    private String password;
}
