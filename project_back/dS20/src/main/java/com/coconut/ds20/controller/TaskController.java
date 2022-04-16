package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.task.ChangeIsJobOrNotInputDto;
import com.coconut.ds20.dto.input.task.TaskCreateInputDto;
import com.coconut.ds20.dto.input.task.TaskUpdateInputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.dto.output.task.TaskDetailOutputDto;
import com.coconut.ds20.dto.output.task.TaskOutputDto;
import com.coconut.ds20.dto.output.task.TaskQueryOutputDto;
import com.coconut.ds20.service.TaskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 23:37
 * File: TaskController
 * Project: dS20
 */

@Api(value = "任务接口", tags = {"任务信息的详情"})
@RestController
@RequestMapping("task")
@UserRight(roles = {"admin", "staff"})
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping()
    ResponseData<List<TaskQueryOutputDto>> query(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, String name, @RequestParam Integer projectId) {
        return taskService.query(pageIndex, pageSize, name, projectId);
    }

    @GetMapping("queryDetailByProjectId")
    ResponseData<List<TaskDetailOutputDto>> queryDetailByProjectId(Integer projectId) {
        return taskService.queryDetailByProjectId(projectId);
    }

    @GetMapping("getById")
    ResponseData<TaskOutputDto> getById(@RequestParam Integer id) {
        return taskService.getById(id);
    }

    @GetMapping("queryByProjectId")
    ResponseData<List<TaskOutputDto>> queryByProjectId(@RequestParam Integer projectId) {
        return taskService.queryByProjectId(projectId);
    }

    @PostMapping()
    ResponseData<TaskOutputDto> create(@RequestBody @Validated TaskCreateInputDto inputDto) {
        return taskService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<TaskOutputDto> update(@RequestBody @Validated TaskUpdateInputDto inputDto) {
        return taskService.update(inputDto);
    }

    @GetMapping("delete")
    ResponseData<Boolean> delete(@RequestParam Integer id) {
        return taskService.delete(id);
    }

    @GetMapping("queryModulesByTaskId")
    ResponseData<List<ModuleOutputDto>> queryModulesByTaskId(@RequestParam Integer taskId) {
        return taskService.queryModulesByTaskId(taskId);
    }

    @PostMapping("changeIsJobOrNot")
    ResponseData<Boolean> changeIsJobOrNot(@RequestBody ChangeIsJobOrNotInputDto inputDto) {
        return taskService.changeIsJobOrNot(inputDto);
    }

    @GetMapping("archive")
    ResponseData<Boolean> archive(@RequestParam Integer id) {
        return taskService.archive(id);
    }
}
