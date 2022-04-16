package com.coconut.ds20.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.job.JobCreateInputDto;
import com.coconut.ds20.dto.input.job.JobUpdateInputDto;
import com.coconut.ds20.dto.input.job.XxlJobInputDto;
import com.coconut.ds20.dto.output.job.JobOutputDto;
import com.coconut.ds20.entity.JobEntity;
import com.coconut.ds20.enums.JobStatusEnum;
import com.coconut.ds20.mapper.JobMapper;
import com.coconut.ds20.service.JobService;
import com.coconut.ds20.util.XxlJobUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 17:29
 * File: JobServiceImpl
 * Project: dS20
 */

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, JobEntity> implements JobService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    XxlJobUtil xxlJobUtil;

    //每个测试任务可以的定时执行数量
    @Value("${per.task.job.count}")
    int perTaskJobCount;

    /**
     * XXL-Job回调业务地址
     */
    private static final String XXL_JOB_CALLBACK_URL="/xxljobcallback/task/run?taskId=%d&environmentId=%d&projectId=%d";


    @Override
    public ResponseData<JobOutputDto> getById(Integer id) {
        ResponseData<JobOutputDto> responseData;
        try {
            JobEntity jobEntity = super.getById(id);

            JobOutputDto outputDto = modelMapper.map(jobEntity,JobOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<JobOutputDto> create(JobCreateInputDto inputDto) {
        ResponseData<JobOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            LambdaQueryWrapper<JobEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(JobEntity::getTaskId,inputDto.getTaskId());
            lambdaQueryWrapper.eq(JobEntity::getIsDelete,false);
            List<JobEntity> jobEntities = this.list(lambdaQueryWrapper);

            if (jobEntities.size()>=perTaskJobCount) {
                checkMsg.add(String.format("测试任务下的定时执行数量已经达到%d个，不能继续创建",perTaskJobCount));
            }
            if(checkMsg.size()>0){
                responseData = new ResponseData <>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            JobEntity jobEntity = modelMapper.map(inputDto,JobEntity.class);
            jobEntity.setStatus(JobStatusEnum.CREATE.getStatus());
            jobEntity.setIsDelete(false);
            this.save(jobEntity);
            JobOutputDto outputDto = modelMapper.map(jobEntity,JobOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<JobOutputDto> update(JobUpdateInputDto inputDto) {
        ResponseData<JobOutputDto> responseData;
        try {
            JobEntity jobEntity = modelMapper.map(inputDto,JobEntity.class);
            jobEntity.setIsDelete(false);
            this.updateById(jobEntity);
            JobOutputDto outputDto = modelMapper.map(jobEntity,JobOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<JobOutputDto> start(Integer id) {
        ResponseData<JobOutputDto> responseData;
        try {
            LambdaQueryWrapper<JobEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!Objects.isNull(id))
                lambdaQueryWrapper.eq(JobEntity::getId,id);
            lambdaQueryWrapper.eq(JobEntity::getIsDelete,false);
            JobEntity jobEntity = this.getOne(lambdaQueryWrapper,true);


            // 设置 xxl-job的输入DTO
            XxlJobInputDto xxlJobInputDto = new XxlJobInputDto();
            xxlJobInputDto.setName(jobEntity.getName());
            xxlJobInputDto.setCron(jobEntity.getCron());
            xxlJobInputDto.setCallbackUrl(String.format(XXL_JOB_CALLBACK_URL,jobEntity.getTaskId(),jobEntity.getEnvironmentId(),jobEntity.getProjectId()));

            Map<String,Object> result = xxlJobUtil.addJob(xxlJobInputDto);
            Integer code = (Integer) result.get("code");
            if(code != 200) {
                responseData = new ResponseData <>();
                responseData.setCode(1);
                responseData.setMessage("启动失败，返回内容为["+ JSON.toJSONString(result));

                return responseData;
            }

            Integer xxljobId = Integer.parseInt(result.get("content").toString());
            jobEntity.setXxlJobId(xxljobId);
            jobEntity.setStatus(JobStatusEnum.START.getStatus());

            this.updateById(jobEntity);

            JobOutputDto outputDto = modelMapper.map(jobEntity,JobOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("jobStart 操作异常：",ex);
            responseData = ResponseData.failure("jobStart 操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<JobOutputDto> stop(Integer id) {
        ResponseData<JobOutputDto> responseData;
        try {
            LambdaQueryWrapper<JobEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!Objects.isNull(id))
                lambdaQueryWrapper.eq(JobEntity::getId,id);
            lambdaQueryWrapper.eq(JobEntity::getIsDelete,false);
            JobEntity jobEntity = this.getOne(lambdaQueryWrapper,true);

            Map<String,Object> result = xxlJobUtil.stopJob(jobEntity.getXxlJobId());
            Integer code = (Integer) result.get("code");
            if(code != 200) {
                responseData = new ResponseData <>();
                responseData.setCode(1);
                responseData.setMessage("停止失败，返回内容为["+ JSON.toJSONString(result));

                return responseData;
            }


            jobEntity.setStatus(JobStatusEnum.STOP.getStatus());

            this.updateById(jobEntity);
            JobOutputDto outputDto = modelMapper.map(jobEntity,JobOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<JobOutputDto> delete(Integer id) {
        ResponseData<JobOutputDto> responseData;
        try {
            JobEntity jobEntity = super.getById(id);
            jobEntity.setIsDelete(true);
            this.updateById(jobEntity);
            JobOutputDto outputDto = modelMapper.map(jobEntity,JobOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }
}
