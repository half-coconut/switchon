package com.coconut.ds20.dto.input.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/6 21:18
 * File: REsetPasswordInputDto
 * Project: dS20
 */

@Data
public class ResetPasswordInputDto {
    private Integer id;
    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
    private String password;
}
