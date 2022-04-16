package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.user.ResetPasswordInputDto;
import com.coconut.ds20.dto.input.user.UserCreateInputDto;
import com.coconut.ds20.dto.input.user.UserUpdateInputDto;
import com.coconut.ds20.dto.output.user.UserOutputDto;
import com.coconut.ds20.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/6 20:13
 * File: UserController
 * Project: dS20
 */

/**
 * 用户控制器
 */
@Api(value = "用户信息接口", tags = {"用户信息的详情"})
@RestController
@RequestMapping("user")
@UserRight(roles = "admin")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseData<List<UserOutputDto>> query(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, String username, String name) {
        return userService.query(pageIndex, pageSize, username, name);
    }

    @UserRight(roles = {"admin","staff"})
    @GetMapping("queryAll")
    public ResponseData<List<UserOutputDto>> queryAll() {
        return userService.queryAll();
    }

    @GetMapping("{id}")
    public ResponseData<UserOutputDto> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping()
    public ResponseData<UserOutputDto> create(@RequestBody @Validated UserCreateInputDto inputDto) {
        return userService.create(inputDto);
    }

    @PutMapping()
    public ResponseData<UserOutputDto> update(@RequestBody @Validated UserUpdateInputDto inputDto) {
        return userService.update(inputDto);
    }

    @DeleteMapping("{id}")
    public ResponseData<Boolean> delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

    @PostMapping("resetPassword")
    public ResponseData<Boolean> resetPassword(@RequestBody @Validated ResetPasswordInputDto inputDto) {
        return userService.resetPassword(inputDto);
    }


}
