package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testrecord.TestRecordCreateInputDto;
import com.coconut.ds20.dto.input.testrecord.TestRecordUpdateInputDto;
import com.coconut.ds20.dto.output.testrecord.TestRecordOutputDto;
import com.coconut.ds20.dto.output.testrecord.TestRecordQueryOutputDto;
import com.coconut.ds20.service.TestRecordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 21:40
 * File: TestRecordController
 * Project: dS20
 */
@Api(value = "运行记录的接口", tags = "运行记录信息的详情")
@RestController
@RequestMapping("testrecord")
@UserRight(roles = {"admin", "staff"})
public class TestRecordController {
    @Autowired
    TestRecordService testRecordService;

    @GetMapping
    ResponseData<List<TestRecordQueryOutputDto>> query(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, Integer environmentId, Integer taskId, @RequestParam Integer projectId) {
        return testRecordService.query(pageIndex, pageSize, environmentId, taskId, projectId);
    }

    @GetMapping("queryByProjectId")
    ResponseData<List<TestRecordQueryOutputDto>> queryByProjectId(Integer taskId, Integer projectId) {
        return testRecordService.queryByProjectId(taskId, projectId);
    }

    @GetMapping("getById")
    ResponseData<TestRecordOutputDto> getById(@RequestParam Integer id) {
        return testRecordService.getById(id);
    }

    @PostMapping("create")
    ResponseData<TestRecordOutputDto> create(@RequestBody @Validated TestRecordCreateInputDto inputDto) {
        return testRecordService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<TestRecordOutputDto> update(@RequestBody @Validated TestRecordUpdateInputDto inputDto) {
        return testRecordService.update(inputDto);
    }
}
