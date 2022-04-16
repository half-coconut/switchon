package com.coconut.ds20.dto.output.account;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/14 15:00
 * File: LoginOutputDto
 * Project: dS20
 */

@Data
public class LoginOutputDto implements Serializable {
    private Integer id;
    private String username;
    private String name;
    private List<String> roles;
}
