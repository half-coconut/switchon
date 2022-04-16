package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testsuite.TestSuiteCreateInputDto;
import com.coconut.ds20.dto.input.testsuite.TestSuiteUpdateInputDto;
import com.coconut.ds20.dto.output.testsuite.TestSuiteOutputDto;
import com.coconut.ds20.service.TestSuiteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 15:33
 * File: TestSuiteController
 * Project: dS20
 */
@Api(value = "测试套件接口",tags = {"测试套件信息的详情"})
@RestController
@RequestMapping("testsuite")
@UserRight(roles = {"admin","staff"})
public class TestSuiteController {

    @Autowired
    TestSuiteService testSuiteService;

    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    @GetMapping("queryByProjectId")
    ResponseData<List<TestSuiteOutputDto>> queryByProjectId(@RequestParam Integer projectId){
        return testSuiteService.queryByProjectId(projectId);
    }

    /**
     * 根据id查询
     * @return
     */
    @GetMapping("getById")
    ResponseData<TestSuiteOutputDto> getById(@RequestParam Integer id){
        return testSuiteService.getById(id);
    }

    /**
     * 创建
     * @param inputDto
     * @return
     */
    @PostMapping("create")
    ResponseData<TestSuiteOutputDto> create(@RequestBody @Validated TestSuiteCreateInputDto inputDto){
        return testSuiteService.create(inputDto);
    }

    /**
     * 更新
     * @param inputDto
     * @return
     */
    @PostMapping("update")
    ResponseData<TestSuiteOutputDto> update(@RequestBody @Validated TestSuiteUpdateInputDto inputDto){
        return testSuiteService.update(inputDto);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete")
    ResponseData<Boolean> delete(@RequestParam Integer id){
        return testSuiteService.delete(id);
    }
}
