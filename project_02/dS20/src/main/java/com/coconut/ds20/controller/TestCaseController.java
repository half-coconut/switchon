package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testcase.TestCaseCreateInputDto;
import com.coconut.ds20.dto.input.testcase.TestCaseUpdateInputDto;
import com.coconut.ds20.dto.output.testcase.TestCaseOutputDto;
import com.coconut.ds20.service.TestCaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 18:26
 * File: TestCaseController
 * Project: dS20
 */
@Api(value = "测试用例接口",tags = "测试用例信息的详情")
@RestController
@RequestMapping("testcase")
@UserRight(roles = {"admin","staff"})
public class TestCaseController {
    @Autowired
    TestCaseService testCaseService;
    @GetMapping("query")
    ResponseData<List<TestCaseOutputDto>> query(@RequestParam(defaultValue = "1") Integer pageIndex,@RequestParam(defaultValue = "10") Integer pageSize,@RequestParam Integer interfaceId,@RequestParam Integer testSuiteId,@RequestParam Integer taskId,@RequestParam Integer projectId){
        return testCaseService.query(pageIndex,pageSize,interfaceId,testSuiteId,taskId,projectId);
    }
    @GetMapping("getById")
    ResponseData<TestCaseOutputDto> getById(@RequestParam Integer id){
        return testCaseService.getById(id);
    }

    @GetMapping("queryByProjectId")
    ResponseData<List<TestCaseOutputDto>> queryByProjectId(@RequestParam Integer projectId){
        return testCaseService.queryByProjectId(projectId);
    }
    @PostMapping("create")
    ResponseData<TestCaseOutputDto> create(@RequestBody @Validated TestCaseCreateInputDto inputDto){
        return testCaseService.create(inputDto);
    }
    @PostMapping("update")
    ResponseData<TestCaseOutputDto> update(@RequestBody @Validated TestCaseUpdateInputDto inputDto){
        return testCaseService.update(inputDto);
    }

    @GetMapping("delete")
    ResponseData<Boolean> delete(@RequestParam Integer id){
        return testCaseService.delete(id);
    }
    @PostMapping("copy")
    ResponseData<TestCaseOutputDto> copy(@RequestBody @Validated TestCaseCreateInputDto inputDto){
        return testCaseService.copy(inputDto);
    }
}
