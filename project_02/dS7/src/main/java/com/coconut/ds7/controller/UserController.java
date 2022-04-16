package com.coconut.ds7.controller;

import com.coconut.ds7.dto.common.RequestData;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.common.page.Paging;
import com.coconut.ds7.dto.input.user.UserCreateInputDto;
import com.coconut.ds7.dto.input.user.UserQueryInputDto;
import com.coconut.ds7.dto.input.user.UserUpdateInputDto;
import com.coconut.ds7.dto.output.user.UserOutputDto;
import com.coconut.ds7.dto.output.version.VersionOutputDto;
import com.coconut.ds7.entity.BugEntity;
import com.coconut.ds7.entity.UserEntity;
import com.coconut.ds7.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/7 21:48
 * File: UserController
 * Project: dS7
 */

@Api(value = "用户接口", tags = {"用户信息及详情"})
@RestController
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "分页查询", notes = "分页查询数据")
    @GetMapping("/")
    public ResponseData<List<UserOutputDto>> query(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "10") Long pageSize, String orderBy, String name, String username) {
        RequestData<UserQueryInputDto> requestData = new RequestData<>();
        Paging paging = new Paging();
        paging.setPgaeIndex(pageIndex);
        paging.setPgaeSize(pageSize);
        requestData.setPage(paging);
        requestData.setOrderBy(orderBy);
        UserQueryInputDto queryInputDto = new UserQueryInputDto();
        queryInputDto.setName(name);
        queryInputDto.setUsername(username);
        requestData.setConditions(queryInputDto);
        return userService.query(requestData);
    }

    @ApiOperation(value = "查询所有", notes = "查询所有数据")
    @GetMapping("/queryAll")
    public ResponseData<List<UserOutputDto>> queryAll() {
        return userService.queryAll();
    }

    @ApiOperation(value = "根据ID获取数据", notes = "根据ID获取数据详情")
    @GetMapping("/{id}")
    public ResponseData<UserOutputDto> getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @ApiOperation(value = "创建", notes = "创建一条新数据")
    @PostMapping("/")
    public ResponseData<UserOutputDto> create(@RequestBody UserCreateInputDto inputDto) {
        return userService.create(inputDto);
    }

    @ApiOperation(value = "修改", notes = "修改一条已存在数据")
    @PutMapping("/")
    public ResponseData<UserOutputDto> update(@RequestBody UserUpdateInputDto inputDto) {
        return userService.update(inputDto);
    }

    @ApiOperation(value = "删除", notes = "删除一条已存在数据")
    @DeleteMapping("/{id}")
    public ResponseData<Boolean> delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

//    @GetMapping("/queryForPage")
//    public ResponseData<List<UserEntity>> queryForPage(int pageSize, int pageIndex,String username, String name, String orderByStr) {
//        return userService.queryForPage(pageSize,pageIndex,username,name,orderByStr);
//    }

//    @GetMapping("/getByUsername")
//    public ResponseData<UserOutputDto> getByUsername(@RequestParam String username) {
//        return userService.getByUsername(username);
//    }


}
