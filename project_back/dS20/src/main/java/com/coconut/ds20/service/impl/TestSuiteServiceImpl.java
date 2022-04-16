package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testsuite.TestSuiteCreateInputDto;
import com.coconut.ds20.dto.input.testsuite.TestSuiteUpdateInputDto;
import com.coconut.ds20.dto.output.testsuite.TestSuiteOutputDto;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.entity.TaskEntity;
import com.coconut.ds20.entity.TestSuiteEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.TestSuiteMapper;
import com.coconut.ds20.service.ProjectService;
import com.coconut.ds20.service.TaskService;
import com.coconut.ds20.service.TestSuiteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 11:57
 * File: TestSuiteServiceImpl
 * Project: dS20
 */

@Service
public class TestSuiteServiceImpl extends ServiceImpl<TestSuiteMapper, TestSuiteEntity> implements TestSuiteService {
    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseData<List<TestSuiteOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<TestSuiteOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(projectId)))
                checkMsg.add(checkProjectId(projectId));

            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<TestSuiteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestSuiteEntity::getProjectId,projectId);
            lambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.orderByDesc(TestSuiteEntity::getId);
            List<TestSuiteEntity> entities = this.list(lambdaQueryWrapper);
            List<TestSuiteOutputDto> outputDtos = entities.stream().map(s->modelMapper.map(s,TestSuiteOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestSuiteOutputDto> getById(Integer id) {
        ResponseData<TestSuiteOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTestSuiteId(id)))
                checkMsg.add(checkTestSuiteId(id));

            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestSuiteEntity entity = super.getById(id);
            TestSuiteOutputDto outputDto = modelMapper.map(entity, TestSuiteOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }


    @Override
    public ResponseData<TestSuiteOutputDto> create(TestSuiteCreateInputDto inputDto) {
        ResponseData<TestSuiteOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTaskId(inputDto.getTaskId())))
                checkMsg.add(checkTaskId(inputDto.getTaskId()));
            if (!Objects.isNull(checkTaskSuiteName(inputDto.getName())))
                checkMsg.add(checkTaskSuiteName(inputDto.getName()));
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            TestSuiteEntity entity = modelMapper.map(inputDto,TestSuiteEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.save(entity);
            TestSuiteOutputDto outputDto = modelMapper.map(entity, TestSuiteOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestSuiteOutputDto> update(TestSuiteUpdateInputDto inputDto) {
        ResponseData<TestSuiteOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTestSuiteId(inputDto.getId())))
                checkMsg.add(checkTestSuiteId(inputDto.getId()));
            if (!Objects.isNull(checkTaskSuiteName(inputDto.getName())))
                checkMsg.add(checkTaskSuiteName(inputDto.getName()));
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTaskId(inputDto.getTaskId())))
                checkMsg.add(checkTaskId(inputDto.getTaskId()));
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            TestSuiteEntity entity = modelMapper.map(inputDto,TestSuiteEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.updateById(entity);
            TestSuiteOutputDto outputDto = modelMapper.map(entity, TestSuiteOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> delete(Integer id) {
        ResponseData<Boolean> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTestSuiteId(id)))
                checkMsg.add(checkTestSuiteId(id));

            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<TestSuiteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestSuiteEntity::getId,id);
            lambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
            TestSuiteEntity testSuiteEntity = this.getOne(lambdaQueryWrapper,true);
            testSuiteEntity.setStatus(StatusEnum.DELETE.getStatus());

            Boolean result = this.updateById(testSuiteEntity);
            responseData = ResponseData.success(result);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    /**
     * 校验测试套件的id是否存在
     * @param id
     * @return
     */
    private String checkTestSuiteId(Integer id){
        LambdaQueryWrapper<TestSuiteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestSuiteEntity::getId,id);
        lambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
        TestSuiteEntity testSuiteEntity = this.getOne(lambdaQueryWrapper,true);
        if (Objects.isNull(testSuiteEntity))
            return "测试套件ID不存在";
        return null;
    }

    /**
     * 校验测试套件名称是否存在
     * @param name
     * @return
     */
    public String checkTaskSuiteName(String name){
        LambdaQueryWrapper<TestSuiteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestSuiteEntity::getName,name);
        lambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
        TestSuiteEntity testSuiteEntity = this.getOne(lambdaQueryWrapper,true);
        if (!Objects.isNull(testSuiteEntity))
            return "测试套件名称已存在";
        return null;
    }

    /**
     * 校验所属项目是否存在
     * @param projectId
     * @return
     */
    public String checkProjectId(Integer projectId) {
        LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ProjectEntity::getId, projectId);
        lambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
        ProjectEntity projectEntity = projectService.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(projectEntity))
            return "所属项目不存在";
        return null;
    }

    /**
     * 校验所属任务是否存在
     * @param taskId
     * @return
     */
    public String checkTaskId(Integer taskId) {
        LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskEntity::getId, taskId);
        lambdaQueryWrapper.eq(TaskEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TaskEntity taskEntity = taskService.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(taskEntity))
            return "所属任务不存在";
        return null;
    }


}
