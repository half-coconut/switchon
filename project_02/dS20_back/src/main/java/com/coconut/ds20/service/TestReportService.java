package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testreport.TestReportCreateInputDto;
import com.coconut.ds20.dto.input.testreport.TestReportUpdateInputDto;
import com.coconut.ds20.dto.output.testreport.TestReportOutputDto;
import com.coconut.ds20.entity.TestReportEntity;

import java.util.List;

public interface TestReportService extends IService<TestReportEntity> {
    ResponseData<List<TestReportOutputDto>> query(Integer pageIndex,Integer pageSize,Integer testRecordId,Integer projectId);
    ResponseData<List<TestReportOutputDto>> queryByProjectId(Integer projectId);
    ResponseData<TestReportOutputDto> getById(Integer id);
    ResponseData<TestReportOutputDto> create(TestReportCreateInputDto inputDto);
    ResponseData<TestReportOutputDto> update(TestReportUpdateInputDto inputDto);
    ResponseData<Boolean> delete(Integer id);
}
