package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testsuite.TestSuiteCreateInputDto;
import com.coconut.ds20.dto.input.testsuite.TestSuiteUpdateInputDto;
import com.coconut.ds20.dto.output.testsuite.TestSuiteOutputDto;
import com.coconut.ds20.entity.TestSuiteEntity;

import java.util.List;

public interface TestSuiteService extends IService<TestSuiteEntity> {
    /**
     * 根据项目id查询
     * @param projectId
     * @return
     */
    ResponseData<List<TestSuiteOutputDto>>  queryByProjectId(Integer projectId);

    /**
     * 根据id查询
     * @return
     */
    ResponseData<TestSuiteOutputDto> getById(Integer id);

    /**
     * 创建
     * @param inputDto
     * @return
     */
    ResponseData<TestSuiteOutputDto> create(TestSuiteCreateInputDto inputDto);

    /**
     * 更新
     * @param inputDto
     * @return
     */
    ResponseData<TestSuiteOutputDto> update(TestSuiteUpdateInputDto inputDto);

    /**
     * 删除
     * @param id
     * @return
     */
    ResponseData<Boolean> delete(Integer id);
}
