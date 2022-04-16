package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.job.JobCreateInputDto;
import com.coconut.ds20.dto.input.job.JobUpdateInputDto;
import com.coconut.ds20.dto.output.job.JobOutputDto;
import com.coconut.ds20.entity.JobEntity;


public interface JobService extends IService<JobEntity> {

    ResponseData<JobOutputDto> getById(Integer id);
    ResponseData<JobOutputDto> create(JobCreateInputDto inputDto);
    ResponseData<JobOutputDto> update(JobUpdateInputDto inputDto);
    ResponseData<JobOutputDto> start(Integer id);
    ResponseData<JobOutputDto> stop(Integer id);
    ResponseData<JobOutputDto> delete(Integer id);
}
