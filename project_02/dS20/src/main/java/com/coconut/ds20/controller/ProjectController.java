package com.coconut.ds20.controller;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:47
 * File: ProjectController
 * Project: dS20
 */

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.project.ProjectCreateInputDto;
import com.coconut.ds20.dto.input.project.ProjectUpdateInputDto;
import com.coconut.ds20.dto.output.project.ProjectOutputDto;
import com.coconut.ds20.service.ProjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目控制器
 */
@Api(value = "项目信息接口", tags = {"项目信息的详情"})
@RestController
@RequestMapping("project")
@UserRight(roles = {"admin", "staff"})
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("queryAll")
    ResponseData<List<ProjectOutputDto>> queryAll() {
        return projectService.queryAll();
    }

    @GetMapping("{id}")
    ResponseData<ProjectOutputDto> getById(@PathVariable Integer id) {
        return projectService.getById(id);
    }

    @PostMapping("create")
    ResponseData<ProjectOutputDto> create(@RequestBody @Validated ProjectCreateInputDto inputDto) {
        return projectService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<ProjectOutputDto> update(@RequestBody @Validated ProjectUpdateInputDto inputDto) {
        return projectService.update(inputDto);
    }

    @GetMapping("delete/{id}")
    ResponseData<Boolean> delete(@PathVariable Integer id) {
        return projectService.delete(id);
    }

}
