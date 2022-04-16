package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testreport.TestReportCreateInputDto;
import com.coconut.ds20.dto.input.testreport.TestReportUpdateInputDto;
import com.coconut.ds20.dto.output.testreport.TestReportOutputDto;
import com.coconut.ds20.service.TestReportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 22:50
 * File: TestReportController
 * Project: dS20
 */
@Api(value = "测试报告的接口",tags = {"测试报告信息的详情"})
@RestController
@RequestMapping("testreport")
@UserRight(roles = {"admin","staff"})
public class TestReportController {
    @Autowired
    TestReportService testReportService;

    @GetMapping("query")
    ResponseData<List<TestReportOutputDto>> query(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam Integer testRecordId, @RequestParam Integer projectId) {
        return testReportService.query(pageIndex, pageSize, testRecordId, projectId);
    }

    @GetMapping("queryByProjectId")
    ResponseData<List<TestReportOutputDto>> queryByProjectId(@RequestParam Integer projectId) {
        return testReportService.queryByProjectId(projectId);
    }

    @GetMapping("getById")
    ResponseData<TestReportOutputDto> getById(@RequestParam Integer id) {
        return testReportService.getById(id);
    }

    @PostMapping("create")
    ResponseData<TestReportOutputDto> create(@RequestBody @Validated TestReportCreateInputDto inputDto) {
        return testReportService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<TestReportOutputDto> update(@RequestBody @Validated TestReportUpdateInputDto inputDto) {
        return testReportService.update(inputDto);
    }

    @GetMapping("delete")
    ResponseData<Boolean> delete(@RequestParam Integer id) {
        return testReportService.delete(id);
    }
}
