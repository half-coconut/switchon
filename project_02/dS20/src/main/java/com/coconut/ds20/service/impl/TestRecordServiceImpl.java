package com.coconut.ds20.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testrecord.TestRecordCreateInputDto;
import com.coconut.ds20.dto.input.testrecord.TestRecordUpdateInputDto;
import com.coconut.ds20.dto.output.task.TaskOutputDto;
import com.coconut.ds20.dto.output.testrecord.TestRecordOutputDto;
import com.coconut.ds20.dto.output.testrecord.TestRecordQueryOutputDto;
import com.coconut.ds20.dto.output.testreport.TestReportOutputDto;
import com.coconut.ds20.dto.output.testreport.TestResultOutputDto;
import com.coconut.ds20.entity.*;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.TestRecordMapper;
import com.coconut.ds20.service.*;
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
 * Date: 2022/4/10 20:59
 * File: TestRecordServiceImpl
 * Project: dS20
 */

@Service
public class TestRecordServiceImpl extends ServiceImpl<TestRecordMapper, TestRecordEntity> implements TestRecordService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    TestReportService testReportService;

    @Override
    public ResponseData<List<TestRecordQueryOutputDto>> query(Integer pageIndex, Integer pageSize, Integer environmentId, Integer taskId, Integer projectId) {
        ResponseData<List<TestRecordQueryOutputDto>> responseData;
        try {
            LambdaQueryWrapper<TestRecordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!Objects.isNull(environmentId))
                lambdaQueryWrapper.eq(TestRecordEntity::getEnvironmentId,environmentId);
            if (!Objects.isNull(taskId))
                lambdaQueryWrapper.eq(TestRecordEntity::getTaskId,taskId);
            if (!Objects.isNull(projectId))
                lambdaQueryWrapper.eq(TestRecordEntity::getProjectId,projectId);
            IPage<TestRecordEntity> queryPage = new Page<>(pageIndex,pageSize);
            queryPage = this.page(queryPage,lambdaQueryWrapper);
            List<TestRecordQueryOutputDto> queryOutputDtos = queryPage.getRecords().stream().map(s->modelMapper.map(s,TestRecordQueryOutputDto.class)).collect(Collectors.toList());

            // 获取所属任务
            List<Integer> taskIds = queryOutputDtos.stream().map(s->s.getTaskId()).collect(Collectors.toList());
            LambdaQueryWrapper<TaskEntity> taskLambdaQueryWrapper = new LambdaQueryWrapper<>();
            taskLambdaQueryWrapper.in(TaskEntity::getId,taskIds);
            List<TaskEntity> taskEntities = taskService.list(taskLambdaQueryWrapper);

            // 获取关联测试报告
            List<Integer> testRecordIds = queryOutputDtos.stream().map(s->s.getId()).collect(Collectors.toList());
            LambdaQueryWrapper<TestReportEntity> reportLambdaQueryWrapper = new LambdaQueryWrapper<>();
            reportLambdaQueryWrapper.in(TestReportEntity::getTestRecordId,testRecordIds);
            List<TestReportEntity> reportEntities = testReportService.list(reportLambdaQueryWrapper);

            // 记录到TestRecordQueryOutputDto中
            queryOutputDtos.stream().forEach(s->{
                TaskEntity taskEntity = taskEntities.stream().filter(t->t.getId().equals(s.getTaskId())).findFirst().orElse(null);
                if (!Objects.isNull(taskEntity)){
                    s.setTask(modelMapper.map(taskEntity, TaskOutputDto.class));
                }
                TestReportEntity reportEntity = reportEntities.stream().filter(t->t.getTestRecordId().equals(s.getId())).findFirst().orElse(null);
                if (!Objects.isNull(reportEntity)){
                    TestReportOutputDto testReportOutputDto = modelMapper.map(reportEntity, TestReportOutputDto.class);
                    TestResultOutputDto testResultOutputDto = modelMapper.map(JSON.parse(testReportOutputDto.getResult()),TestResultOutputDto.class);
                    testReportOutputDto.setTotalOfTestCase(testResultOutputDto.getTotalOfTestCase());
                    testReportOutputDto.setTotalOfTestCaseForSuccess(testResultOutputDto.getTotalOfTestCaseForSuccess());
                    testReportOutputDto.setTotalOfTestCaseForFailure(testResultOutputDto.getTotalOfTestCaseForFailure());
                    testReportOutputDto.setTotalOfTestCaseForError(testResultOutputDto.getTotalOfTestCaseForError());

                    testReportOutputDto.setResult(null);
                    s.setTestReport(testReportOutputDto);
                }

            });

            responseData = ResponseData.success(queryOutputDtos);
            responseData.setTotal(queryPage.getTotal());
        }catch (Exception ex){
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<TestRecordQueryOutputDto>> queryByProjectId(Integer taskId, Integer projectId) {
        ResponseData<List<TestRecordQueryOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(projectId)))
                checkMsg.add(checkProjectId(projectId));
            if (!Objects.isNull(checkTaskId(taskId)))
                checkMsg.add(checkTaskId(taskId));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<TestRecordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestRecordEntity::getProjectId, projectId);
            lambdaQueryWrapper.eq(TestRecordEntity::getIsDelete, false);
            lambdaQueryWrapper.orderByDesc(TestRecordEntity::getId);
            List<TestRecordEntity> entities = this.list(lambdaQueryWrapper);
            List<TestRecordQueryOutputDto> outputDtos = entities.stream().map(s -> modelMapper.map(s, TestRecordQueryOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestRecordOutputDto> getById(Integer id) {
        ResponseData<TestRecordOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTestRecordId(id)))
                checkMsg.add(checkTestRecordId(id));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestRecordEntity entity = super.getById(id);
            TestRecordOutputDto outputDto = modelMapper.map(entity, TestRecordOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestRecordOutputDto> create(TestRecordCreateInputDto inputDto) {
        ResponseData<TestRecordOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkEnvironmentId(inputDto.getEnvironmentId())))
                checkMsg.add(checkEnvironmentId(inputDto.getEnvironmentId()));
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTaskId(inputDto.getTaskId())))
                checkMsg.add(checkTaskId(inputDto.getTaskId()));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestRecordEntity entity = modelMapper.map(inputDto, TestRecordEntity.class);
            entity.setIsDelete(false);
            this.save(entity);
            TestRecordOutputDto outputDto = modelMapper.map(entity, TestRecordOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestRecordOutputDto> update(TestRecordUpdateInputDto inputDto) {
        ResponseData<TestRecordOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkEnvironmentId(inputDto.getEnvironmentId())))
                checkMsg.add(checkEnvironmentId(inputDto.getEnvironmentId()));
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTaskId(inputDto.getTaskId())))
                checkMsg.add(checkTaskId(inputDto.getTaskId()));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestRecordEntity entity = modelMapper.map(inputDto, TestRecordEntity.class);
            entity.setIsDelete(false);
            this.updateById(entity);
            TestRecordOutputDto outputDto = modelMapper.map(entity, TestRecordOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    /**
     * 校验 运行记录ID是否存在
     *
     * @param id
     * @return
     */
    public String checkTestRecordId(Integer id) {
        LambdaQueryWrapper<TestRecordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestRecordEntity::getId, id);
        lambdaQueryWrapper.eq(TestRecordEntity::getIsDelete, false);
        TestRecordEntity testRecordEntity = this.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(testRecordEntity))
            return "运行记录ID不存在";
        return null;
    }

    /**
     * 校验所属项目是否存在
     *
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
     * 校验 所属任务是否存在
     *
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

    /**
     * 校验 所属项目环境是否存在
     *
     * @param environmentId
     * @return
     */
    public String checkEnvironmentId(Integer environmentId) {
        LambdaQueryWrapper<EnvironmentEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EnvironmentEntity::getId, environmentId);
        lambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
        EnvironmentEntity environmentEntity = environmentService.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(environmentEntity))
            return "所属项目环境不存在";
        return null;
    }
}
