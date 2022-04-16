package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.job.JobCreateInputDto;
import com.coconut.ds20.dto.input.job.JobUpdateInputDto;
import com.coconut.ds20.dto.output.job.JobOutputDto;
import com.coconut.ds20.service.JobService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/12 18:34
 * File: JobController
 * Project: dS20
 */
@Api(value = "定时任务的接口", tags = {"定时任务信息的详情"})
@RestController
@RequestMapping("job")
@UserRight(roles = {"admin","staff"})
public class JobController {
    @Autowired
    JobService jobService;

    @GetMapping("getById")
    ResponseData<JobOutputDto> getById(@RequestParam Integer id) {
        return jobService.getById(id);
    }

    @PostMapping("create")
    ResponseData<JobOutputDto> create(@RequestBody @Validated JobCreateInputDto inputDto) {
        return jobService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<JobOutputDto> update(@RequestBody @Validated JobUpdateInputDto inputDto) {
        return jobService.update(inputDto);
    }

    @GetMapping("start")
    ResponseData<JobOutputDto> start(@RequestParam Integer id) {
        return jobService.start(id);
    }

    @GetMapping("stop")
    ResponseData<JobOutputDto> stop(@RequestParam Integer id) {
        return jobService.stop(id);
    }

    @GetMapping("delete")
    ResponseData<JobOutputDto> delete(@RequestParam Integer id) {
        return jobService.delete(id);
    }
}
