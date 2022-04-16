package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.module.ModuleCreateInputDto;
import com.coconut.ds20.dto.input.module.ModuleUpdateInputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.service.ModuleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 23:45
 * File: ModuleController
 * Project: dS20
 */
@Api(value = "模块接口", tags = {"模块信息的详情"})
@RestController
@RequestMapping("module")
@UserRight(roles = {"admin", "staff"})
public class ModuleController {
    @Autowired
    ModuleService moduleService;

    @GetMapping("query")
    ResponseData<List<ModuleOutputDto>> query(@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam(defaultValue = "1") Integer pageIndex,@RequestParam Integer projectId){
        return moduleService.query(pageSize,pageIndex,projectId);
    }

    @GetMapping("queryByProjectId")
    ResponseData<List<ModuleOutputDto>> queryByProjectId(@RequestParam Integer projectId){
        return moduleService.queryByProjectId(projectId);
    }

    @GetMapping("getById/{id}")
    ResponseData<ModuleOutputDto> getById(@PathVariable Integer id){
        return moduleService.getById(id);
    }

    @PostMapping("create")
    ResponseData<ModuleOutputDto> create(@RequestBody @Validated ModuleCreateInputDto inputDto) {
        return moduleService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<ModuleOutputDto> update(@RequestBody @Validated ModuleUpdateInputDto inputDto){
        return moduleService.update(inputDto);
    }

    @GetMapping("delete/{id}")
    ResponseData<Boolean> delete(@PathVariable Integer id){
        return moduleService.delete(id);
    }

}
