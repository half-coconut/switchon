package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testrecord.TestRecordCreateInputDto;
import com.coconut.ds20.dto.input.testrecord.TestRecordUpdateInputDto;
import com.coconut.ds20.dto.output.testrecord.TestRecordOutputDto;
import com.coconut.ds20.dto.output.testrecord.TestRecordQueryOutputDto;
import com.coconut.ds20.entity.TestRecordEntity;

import java.util.List;

public interface TestRecordService extends IService<TestRecordEntity> {
    ResponseData<List<TestRecordQueryOutputDto>> query(Integer pageIndex,Integer pageSize,Integer environmentId,Integer taskId,Integer projectId);
    ResponseData<List<TestRecordQueryOutputDto>> queryByProjectId(Integer taskId,Integer projectId);
    ResponseData<TestRecordOutputDto> getById(Integer id);
    ResponseData<TestRecordOutputDto> create(TestRecordCreateInputDto inputDto);
    ResponseData<TestRecordOutputDto> update(TestRecordUpdateInputDto inputDto);
}
