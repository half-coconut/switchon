package com.coconut.ds20.controller;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/16 11:32
 * File: AccountController
 * Project: dS20
 */

import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.account.LoginInputDto;
import com.coconut.ds20.dto.output.account.LoginOutputDto;
import com.coconut.ds20.service.AccountService;
import com.coconut.ds20.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 账号控制器，可以做登录、注册和生成验证码的图片
 */
@Api(value = "账号的接口",tags = {"账号信息的详情"})
@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("login")
    public ResponseData<LoginOutputDto> login(@RequestBody @Validated LoginInputDto inputDto){
        return accountService.login(inputDto);
    }
}
