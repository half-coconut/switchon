package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testcase.TestCaseCreateInputDto;
import com.coconut.ds20.dto.input.testcase.TestCaseUpdateInputDto;
import com.coconut.ds20.dto.output.testcase.TestCaseOutputDto;
import com.coconut.ds20.entity.TestCaseEntity;

import java.util.List;

public interface TestCaseService extends IService<TestCaseEntity> {
    ResponseData<List<TestCaseOutputDto>> query(Integer pageIndex,Integer pageSize,Integer interfaceId,Integer testSuiteId,Integer taskId,Integer projectId);
    ResponseData<TestCaseOutputDto> getById(Integer id);
    ResponseData<List<TestCaseOutputDto>> queryByProjectId(Integer projectId);
    ResponseData<TestCaseOutputDto> create(TestCaseCreateInputDto inputDto);
    ResponseData<TestCaseOutputDto> update(TestCaseUpdateInputDto inputDto);
    ResponseData<Boolean> delete(Integer id);
    ResponseData<TestCaseOutputDto> copy(TestCaseCreateInputDto inputDto);
}
