package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.environment.EnvironmentCreateInputDto;
import com.coconut.ds20.dto.input.environment.EnvironmentUpdateInputDto;
import com.coconut.ds20.dto.output.environment.EnvironmentOutputDto;
import com.coconut.ds20.service.EnvironmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 1:20
 * File: EnvironmentController
 * Project: dS20
 */
@Api(value = "环境接口",tags = {"环境信息的详情"})
@RestController
@RequestMapping("environment")
@UserRight(roles = {"admin","staff"})
public class EnvironmentController {
    @Autowired
    EnvironmentService environmentService;

    @GetMapping("queryByProjectId")
    ResponseData<List<EnvironmentOutputDto>> queryByProjectId(@RequestParam Integer projectId){
        return environmentService.queryByProjectId(projectId);
    }
    @GetMapping("getById")
    ResponseData<EnvironmentOutputDto> getById(@RequestParam Integer id){
        return environmentService.getById(id);
    }

    @PostMapping("create")
    ResponseData<EnvironmentOutputDto> create(@RequestBody EnvironmentCreateInputDto inputDto){
        return environmentService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<EnvironmentOutputDto> update(@RequestBody EnvironmentUpdateInputDto inputDto){
        return environmentService.update(inputDto);
    }

    @GetMapping("delete")
    ResponseData<Boolean> delete(Integer id){
        return environmentService.delete(id);
    }
}
