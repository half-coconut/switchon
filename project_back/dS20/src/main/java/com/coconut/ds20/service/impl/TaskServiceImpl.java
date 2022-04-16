package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.task.ChangeIsJobOrNotInputDto;
import com.coconut.ds20.dto.input.task.TaskCreateInputDto;
import com.coconut.ds20.dto.input.task.TaskRunInputDto;
import com.coconut.ds20.dto.input.task.TaskUpdateInputDto;
import com.coconut.ds20.dto.output.job.JobOutputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.dto.output.task.TaskDetailOutputDto;
import com.coconut.ds20.dto.output.task.TaskOutputDto;
import com.coconut.ds20.dto.output.task.TaskQueryOutputDto;
import com.coconut.ds20.dto.output.testcase.TestCaseOutputDto;
import com.coconut.ds20.dto.output.testsuite.TestSuiteDetailOutputDto;
import com.coconut.ds20.entity.*;
import com.coconut.ds20.enums.JobStatusEnum;
import com.coconut.ds20.enums.RecordStatusEnum;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.TaskMapper;
import com.coconut.ds20.service.*;
import com.coconut.ds20.util.SessionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 23:18
 * File: TaskServiceImpl
 * Project: dS20
 */

/**
 * 任务服务实现类
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, TaskEntity> implements TaskService {
    @Autowired
    ProjectService projectService;

    @Autowired
    ModuleService moduleService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TaskModuleService taskModuleService;

    @Autowired
    JobService jobService;

    @Autowired
    TestSuiteService testSuiteService;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    TestRecordService testRecordService;

    @Autowired
    EnvironmentService environmentService;

    @Autowired
    InterfaceService interfaceService;

    @Autowired
    SessionUtil sessionUtil;

    @Autowired
    TaskTestService taskTestService;

    @Override
    public ResponseData<List<TaskQueryOutputDto>> query(Integer pageIndex, Integer pageSize, String name, Integer projectId) {
        ResponseData<List<TaskQueryOutputDto>> responseData;
        try {
            LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!Objects.isNull(name))
                lambdaQueryWrapper.eq(TaskEntity::getName,name);
            if (!Objects.isNull(projectId))
                lambdaQueryWrapper.eq(TaskEntity::getProjectId,projectId);
            lambdaQueryWrapper.eq(TaskEntity::getStatus,StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.orderByDesc(TaskEntity::getId);
            IPage<TaskEntity> queryPage = new Page<>(pageIndex,pageSize);
            queryPage = this.page(queryPage,lambdaQueryWrapper);
            List<TaskEntity> taskEntities = queryPage.getRecords();

            // 获取关联模块ID
            LambdaQueryWrapper<TaskModuleEntity> tMLambdaQueryWrapper = new LambdaQueryWrapper<>();
            List<Integer> taskIds = taskEntities.stream().map(s->s.getId()).collect(Collectors.toList());
            tMLambdaQueryWrapper.in(TaskModuleEntity::getTaskId,taskIds);
            List<TaskModuleEntity> taskModuleEntities = taskModuleService.list(tMLambdaQueryWrapper);
            List<Integer> moduleIds = taskModuleEntities.stream().map(s->s.getModuleId()).collect(Collectors.toList());

            // 通过模块ID，获取模块List<Entity>
            LambdaQueryWrapper<ModuleEntity> mLambdaQueryWrapper = new LambdaQueryWrapper<>();
            mLambdaQueryWrapper.in(ModuleEntity::getId,moduleIds);
            List<ModuleEntity> moduleEntities = moduleService.list(mLambdaQueryWrapper);
            // 获取定时任务
            LambdaQueryWrapper<JobEntity> jLambdaQueryWrapper = new LambdaQueryWrapper<>();
            jLambdaQueryWrapper.in(JobEntity::getTaskId,taskIds);
            jLambdaQueryWrapper.eq(JobEntity::getIsDelete,false);
            List<JobEntity> jobEntities = jobService.list(jLambdaQueryWrapper);

            // 设置关联模块详情和job详情
            List<TaskQueryOutputDto> outputDtos = taskEntities.stream().map(s->modelMapper.map(s,TaskQueryOutputDto.class)).collect(Collectors.toList());
            outputDtos.stream().forEach(s->{
                // 当前任务id 相等的 任务-模块 实体list
                List<TaskModuleEntity> currentTaskModuleEntities = taskModuleEntities.stream().filter(t->t.getTaskId() == s.getId()).collect(Collectors.toList());
                // 当前任务id下，关联的moduleIds
                List<Integer> currentModuleIds = currentTaskModuleEntities.stream().map(t->t.getModuleId()).collect(Collectors.toList());
                List<ModuleEntity> moduleEntityList = moduleEntities.stream().filter(t->currentModuleIds.contains(t.getId())).collect(Collectors.toList());
                List<ModuleOutputDto> moduleOutputDtos = moduleEntityList.stream().map(t->modelMapper.map(t,ModuleOutputDto.class)).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(moduleOutputDtos)){
                    s.setModules(moduleOutputDtos);
                }
                // 当前任务下的job详情
                List<JobEntity> currentJobEntities = jobEntities.stream().filter(t->t.getTaskId().equals(s.getId())).collect(Collectors.toList());
                List<JobOutputDto> jobOutputDtos = currentJobEntities.stream().map(t->modelMapper.map(t,JobOutputDto.class)).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(jobOutputDtos)){
                    s.setJobs(jobOutputDtos);
                }
            });
            responseData = ResponseData.success(outputDtos);
            responseData.setTotal(queryPage.getTotal());

        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<TaskDetailOutputDto>> queryDetailByProjectId(Integer projectId) {
        ResponseData<List<TaskDetailOutputDto>> responseData;
        try {
            LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!Objects.isNull(projectId))
                lambdaQueryWrapper.eq(TaskEntity::getProjectId,projectId);
            lambdaQueryWrapper.eq(TaskEntity::getStatus,StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.eq(TaskEntity::getIsArchive,false);
            List<TaskEntity> taskEntities = this.list(lambdaQueryWrapper);
            List<TaskDetailOutputDto> outputDtos = taskEntities.stream().map(s->modelMapper.map(s,TaskDetailOutputDto.class)).collect(Collectors.toList());

            // 根据projectId 查询 testSuiteEntity
            LambdaQueryWrapper<TestSuiteEntity> suiteLambdaQueryWrapper = new LambdaQueryWrapper<>();
            suiteLambdaQueryWrapper.eq(TestSuiteEntity::getProjectId,projectId);
            suiteLambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
            List<TestSuiteEntity> suiteEntities = testSuiteService.list(suiteLambdaQueryWrapper);

            // 根据projectId 查询 testCaseEntity
            LambdaQueryWrapper<TestCaseEntity> caseLambdaQueryWrapper = new LambdaQueryWrapper<>();
            caseLambdaQueryWrapper.eq(TestCaseEntity::getProjectId,projectId);
            caseLambdaQueryWrapper.eq(TestCaseEntity::getStatus,StatusEnum.ENABLE.getStatus());
            List<TestCaseEntity> caseEntities = testCaseService.list(caseLambdaQueryWrapper);

            // 设置taskSuite 和testCase
            outputDtos.stream().forEach(s->{
                // 获取当前测试套件
                List<TestSuiteDetailOutputDto> testSuiteDetailOutputDtos = suiteEntities.stream().filter(t->t.getTaskId()==s.getId()).map(t->modelMapper.map(t,TestSuiteDetailOutputDto.class)).collect(Collectors.toList());
                testSuiteDetailOutputDtos.stream().forEach(t->{
                    // 获取当前测试用例
                    List<TestCaseOutputDto> testCaseOutputDtos = caseEntities.stream().filter(k->k.getTestSuiteId() == t.getId()).map(k->modelMapper.map(k,TestCaseOutputDto.class)).collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(testCaseOutputDtos)){
                        t.setTestCases(testCaseOutputDtos);
                    }
                });
                if (!CollectionUtils.isEmpty(testSuiteDetailOutputDtos)){
                    s.setTestSuits(testSuiteDetailOutputDtos);
                }
            });
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());

        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<TaskOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<TaskOutputDto>>  responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(projectId)))
                checkMsg.add(checkProjectId(projectId));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskEntity::getProjectId,projectId);
            lambdaQueryWrapper.eq(TaskEntity::getStatus,StatusEnum.ENABLE.getStatus());
            List<TaskEntity> taskEntities = this.list(lambdaQueryWrapper);
            List<TaskOutputDto> outputDtos = taskEntities.stream().map(s->modelMapper.map(s,TaskOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        }catch (Exception ex){
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<TaskOutputDto> getById(Integer id) {
        ResponseData<TaskOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTaskId(id)))
                checkMsg.add(checkTaskId(id));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            TaskEntity entity = super.getById(id);
            // 获取关联的模块list<Integer> moduleIds
            LambdaQueryWrapper<TaskModuleEntity> lambdaQueryWrapper =new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskModuleEntity::getTaskId,id);
            List<TaskModuleEntity> taskModuleEntityList = taskModuleService.list(lambdaQueryWrapper);

            List<Integer> moduleIds = new ArrayList<>();
            taskModuleEntityList.forEach(s->{
                moduleIds.add(s.getModuleId());
            });

            TaskOutputDto outputDto = modelMapper.map(entity, TaskOutputDto.class);
            // 设置返回的 moduleIds
            outputDto.setModuleIds(moduleIds);

            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TaskOutputDto> create(TaskCreateInputDto inputDto) {
        ResponseData<TaskOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTaskName(inputDto.getName(), inputDto.getProjectId())))
                checkMsg.add(checkTaskName(inputDto.getName(), inputDto.getProjectId()));
            if (!Objects.isNull(checkModuleIds(inputDto.getModuleIds())))
                checkMsg.add(checkModuleIds(inputDto.getModuleIds()));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }

            TaskEntity taskEntity = modelMapper.map(inputDto, TaskEntity.class);
            taskEntity.setStatus(StatusEnum.ENABLE.getStatus());
            taskEntity.setIsArchive(false);
            this.save(taskEntity);
            // 保存关联模块 到 task_module关联表
            if (!CollectionUtils.isEmpty(inputDto.getModuleIds()))
                inputDto.getModuleIds().forEach(s -> {
                    TaskModuleEntity taskModuleEntity = new TaskModuleEntity();
                    taskModuleEntity.setTaskId(taskEntity.getId());
                    taskModuleEntity.setModuleId(s);

                    taskModuleService.save(taskModuleEntity);
                });

            TaskOutputDto outputDto = modelMapper.map(taskEntity, TaskOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TaskOutputDto> update(TaskUpdateInputDto inputDto) {
        ResponseData<TaskOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTaskId(inputDto.getId())))
                checkMsg.add(checkTaskId(inputDto.getId()));
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTaskName(inputDto.getName(), inputDto.getProjectId())))
                checkMsg.add(checkTaskName(inputDto.getName(), inputDto.getProjectId()));
            if (!Objects.isNull(checkModuleIds(inputDto.getModuleIds())))
                checkMsg.add(checkModuleIds(inputDto.getModuleIds()));
            if (!Objects.isNull(checkTaskModuleIds(inputDto)))
                checkMsg.add(checkTaskModuleIds(inputDto));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }

            TaskEntity taskEntity = modelMapper.map(inputDto, TaskEntity.class);
            taskEntity.setStatus(StatusEnum.ENABLE.getStatus());
            taskEntity.setIsArchive(false);
            // 更新任务
            this.updateById(taskEntity);
            // 保存新增的关联模块ID 到 task_module关联表
            LambdaQueryWrapper<TaskModuleEntity> lambdaQueryWrapper =new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskModuleEntity::getTaskId,inputDto.getId());
            List<TaskModuleEntity> taskModuleEntityList = taskModuleService.list(lambdaQueryWrapper);

            if (!CollectionUtils.isEmpty(inputDto.getModuleIds())){
                // 过滤已存在的task_module_id
                List<Integer> nonExistModuleIds = inputDto.getModuleIds().stream().filter(s->taskModuleEntityList.stream().filter(t->t.getModuleId() ==s).count()<=0).collect(Collectors.toList());
                // 将更新的moduleIds存入task_module表
                nonExistModuleIds.forEach(s -> {
                    TaskModuleEntity taskModuleEntity = new TaskModuleEntity();
                    taskModuleEntity.setTaskId(taskEntity.getId());
                    taskModuleEntity.setModuleId(s);

                    taskModuleService.save(taskModuleEntity);
                });
            }

            TaskOutputDto outputDto = modelMapper.map(taskEntity, TaskOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> delete(Integer id) {
        ResponseData<Boolean> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTaskId(id)))
                checkMsg.add(checkTaskId(id));
            if (!Objects.isNull(checkTestRecord(id)))
                checkMsg.add(checkTestRecord(id));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskEntity::getId,id);
            lambdaQueryWrapper.eq(TaskEntity::getStatus,StatusEnum.ENABLE.getStatus());
            TaskEntity taskEntity = this.getOne(lambdaQueryWrapper);
            taskEntity.setStatus(StatusEnum.DELETE.getStatus());
            Boolean result = this.updateById(taskEntity);
            responseData = ResponseData.success(result);

        }catch (Exception ex){
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<List<ModuleOutputDto>> queryModulesByTaskId(Integer taskId) {
        ResponseData<List<ModuleOutputDto>>  responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTaskId(taskId)))
                checkMsg.add(checkTaskId(taskId));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            LambdaQueryWrapper<TaskModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskModuleEntity::getTaskId,taskId);
            List<TaskModuleEntity> taskEntities = taskModuleService.list(lambdaQueryWrapper);
            List<Integer> moduleIds  = taskEntities.stream().map(s->s.getModuleId()).collect(Collectors.toList());

            LambdaQueryWrapper<ModuleEntity> moduleEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
            moduleEntityLambdaQueryWrapper.in(ModuleEntity::getId, moduleIds);
            moduleEntityLambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            List<ModuleEntity> moduleEntities = moduleService.list(moduleEntityLambdaQueryWrapper);

            List<ModuleOutputDto> outputDtos = moduleEntities.stream().map(s->modelMapper.map(s,ModuleOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        }catch (Exception ex){
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<Boolean> run(TaskRunInputDto taskRunInputDto) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        List<String> checkMsg = new ArrayList<>();
        try {
            // 入参校验：是否存在 任务、项目和环境
            if (StringUtils.isNotEmpty(checkTaskId(taskRunInputDto.getTaskId())))
                checkMsg.add(checkTaskId(taskRunInputDto.getTaskId()));
            if (StringUtils.isNotEmpty(checkProjectId(taskRunInputDto.getProjectId())))
                checkMsg.add(checkProjectId(taskRunInputDto.getProjectId()));
            if(StringUtils.isNotEmpty(checkEnvironmentId(taskRunInputDto.getEnvironmentId())));
                checkMsg.add(checkEnvironmentId(taskRunInputDto.getEnvironmentId()));
            // 基础数据校验：
            // 1.校验 task 是否关联有模块 --> 校验 module 是否有 接口
            if (StringUtils.isNotEmpty(checkModuleAndInterface(taskRunInputDto.getTaskId())))
                checkMsg.add(checkModuleAndInterface(taskRunInputDto.getTaskId()));
            // 2.校验 task 是否关联测试套件 --> 校验 testSuite 是否有 testCase
            if (StringUtils.isNotEmpty(checkTestSuiteAndTestCase(taskRunInputDto.getTaskId())))
                checkMsg.add(checkTestSuiteAndTestCase(taskRunInputDto.getTaskId()));
        }catch (Exception ex){
            log.error("获取任务的基础数据异常",ex);
            checkMsg.add("获取任务的基础数据异常");
        }
        if (CollectionUtils.isNotEmpty(checkMsg)){
            responseData = new ResponseData<>();
            responseData.setCode(1);
            responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
            return responseData;
        }
        // 新增一个测试记录，状态为 执行中
        TestRecordEntity testRecordEntity = modelMapper.map(taskRunInputDto,TestRecordEntity.class);
        testRecordEntity.setRecordStatus(RecordStatusEnum.RUNNING.getStatus());
        testRecordEntity.setIsDelete(false);

        UserEntity userEntity = sessionUtil.getCurrentUser().getUserEntity();
        Date currentDate = new Date();
        testRecordEntity.setCreateById(userEntity.getId());
        testRecordEntity.setCreateByName(userEntity.getName());
        testRecordEntity.setCreateTime(currentDate);
        testRecordEntity.setUpdateById(userEntity.getId());
        testRecordEntity.setCreateByName(userEntity.getName());
        testRecordEntity.setUpdateTime(currentDate);
        testRecordService.save(testRecordEntity);

        EnvironmentEntity environmentEntityntity = getEnvironmentEntity(taskRunInputDto.getEnvironmentId());
        List<InterfaceEntity> interfaceEntities = getInterfaceEntities(taskRunInputDto.getTaskId());
        List<TestCaseEntity> testCaseEntities = getTestCaseEntities(taskRunInputDto.getTaskId());
        SessionUtil.CurrentUser currentUser = sessionUtil.getCurrentUser();

        // 开启一个线程池，执行 TaskTest.test()
        new Thread(new Runnable() {
            @Override
            public void run() {
                taskTestService.test(environmentEntityntity,interfaceEntities,testCaseEntities,testRecordEntity,currentUser);
            }
        }).start();

        responseData = ResponseData.success();
        return responseData;
    }

    @Override
    public ResponseData<Boolean> changeIsJobOrNot(ChangeIsJobOrNotInputDto inputDto) {
        ResponseData<Boolean> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTaskId(inputDto.getTaskId())))
                checkMsg.add(checkTaskId(inputDto.getTaskId()));
            if (!Objects.isNull(checkIsJob(inputDto.getTaskId())))
                checkMsg.add(checkIsJob(inputDto.getTaskId()));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskEntity::getId,inputDto.getTaskId());
            lambdaQueryWrapper.eq(TaskEntity::getStatus,StatusEnum.ENABLE.getStatus());
            TaskEntity taskEntity = this.getOne(lambdaQueryWrapper);

            taskEntity.setIsJob(inputDto.getIsJob());
            Boolean result = this.updateById(taskEntity);
            responseData = ResponseData.success(result);

        }catch (Exception ex){
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<Boolean> archive(Integer id) {
        ResponseData<Boolean> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTaskId(id)))
                checkMsg.add(checkTaskId(id));
            if (!Objects.isNull(checkIsJob(id)))
                checkMsg.add(checkIsJob(id));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TaskEntity::getId,id);
            lambdaQueryWrapper.eq(TaskEntity::getStatus,StatusEnum.ENABLE.getStatus());
            TaskEntity taskEntity = this.getOne(lambdaQueryWrapper);

            taskEntity.setIsArchive(true);
            Boolean result = this.updateById(taskEntity);
            responseData = ResponseData.success(result);

        }catch (Exception ex){
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
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
     * 校验任务是否存在
     * @param id
     * @return
     */
    public String checkTaskId(Integer id) {
        LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskEntity::getId, id);
        lambdaQueryWrapper.eq(TaskEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TaskEntity taskEntity = this.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(taskEntity))
            return "任务ID不存在";
        return null;
    }

    public EnvironmentEntity getEnvironmentEntity(Integer envId){
        LambdaQueryWrapper<EnvironmentEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EnvironmentEntity::getId, envId);
        lambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
        EnvironmentEntity environmentEntityntity = environmentService.getOne(lambdaQueryWrapper, true);
        return environmentEntityntity;
    }

    /**
     * 校验环境是否存在
     * @param envId
     * @return
     */
    public String checkEnvironmentId(Integer envId) {
        EnvironmentEntity environmentEntityntity = getEnvironmentEntity(envId);
        if (Objects.isNull(environmentEntityntity))
            return "测试环境ID不存在";
        return null;
    }

    public List<TaskModuleEntity> getTaskModuleEntities(Integer taskId){
        LambdaQueryWrapper<TaskModuleEntity> tmlambdaQueryWrapper = new LambdaQueryWrapper<>();
        tmlambdaQueryWrapper.eq(TaskModuleEntity::getTaskId,taskId);
        List<TaskModuleEntity> taskModuleEntities = taskModuleService.list(tmlambdaQueryWrapper);
        return taskModuleEntities;
    }

    public List<InterfaceEntity> getInterfaceEntities(Integer taskId){
        List<TaskModuleEntity> taskModuleEntities = getTaskModuleEntities(taskId);
        List<Integer> moduleIds = taskModuleEntities.stream().map(s->s.getModuleId()).collect(Collectors.toList());
        LambdaQueryWrapper<InterfaceEntity> ilambdaQueryWrapper = new LambdaQueryWrapper<>();
        ilambdaQueryWrapper.in(InterfaceEntity::getModuleId,moduleIds);
        ilambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
        List<InterfaceEntity> interfaceEntities = interfaceService.list(ilambdaQueryWrapper);
        return interfaceEntities;
    }

    /**
     * 根据task_id 校验 task 是否关联有模块 --> 校验 module 是否有接口
     * @param taskId
     * @return
     */
    public String checkModuleAndInterface(Integer taskId){
        TaskEntity taskEntity = super.getById(taskId);
        List<TaskModuleEntity> taskModuleEntities = getTaskModuleEntities(taskId);
        if (CollectionUtils.isNotEmpty(taskModuleEntities)){
            List<InterfaceEntity> interfaceEntities = getInterfaceEntities(taskId);
            if (CollectionUtils.isEmpty(interfaceEntities))
                return String.format("当前任务[%s]下的模块，没有接口",taskEntity.getName());
        }else {
            return String.format("当前任务[%s]下没有关联模块",taskEntity.getName());
        }
        return null;
    }

    public List<TestSuiteEntity> getTestSuiteEntities(Integer taskId){
        LambdaQueryWrapper<TestSuiteEntity> TSlambdaQueryWrapper = new LambdaQueryWrapper<>();
        TSlambdaQueryWrapper.eq(TestSuiteEntity::getTaskId,taskId);
        TSlambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
        List<TestSuiteEntity> testSuiteEntities = testSuiteService.list(TSlambdaQueryWrapper);
        return testSuiteEntities;
    }

    public List<TestCaseEntity> getTestCaseEntities(Integer taskId){
        List<TestSuiteEntity> testSuiteEntities = getTestSuiteEntities(taskId);
        List<Integer> testSuiteIds = testSuiteEntities.stream().map(s->s.getId()).collect(Collectors.toList());
        LambdaQueryWrapper<TestCaseEntity> TClambdaQueryWrapper = new LambdaQueryWrapper<>();
        TClambdaQueryWrapper.eq(TestCaseEntity::getTaskId,taskId);
        TClambdaQueryWrapper.in(TestCaseEntity::getTestSuiteId,testSuiteIds);
        TClambdaQueryWrapper.eq(TestCaseEntity::getStatus,StatusEnum.ENABLE.getStatus());
        List<TestCaseEntity> testCaseEntities = testCaseService.list(TClambdaQueryWrapper);
        return testCaseEntities;
    }

    /**
     * 根据task_id 校验 task 是否关联测试套件 --> 校验 testSuite 是否有 testCase
     * @param taskId
     * @return
     */
    public String checkTestSuiteAndTestCase(Integer taskId){
        TaskEntity taskEntity = super.getById(taskId);
        List<TestSuiteEntity> testSuiteEntities = getTestSuiteEntities(taskId);
        if (CollectionUtils.isNotEmpty(testSuiteEntities)){
            List<TestCaseEntity> testCaseEntities = getTestCaseEntities(taskId);
            if (CollectionUtils.isEmpty(testCaseEntities))
                return String.format("当前任务[%s]下的测试套件，没有测试用例",taskEntity.getName());
        }else {
            return String.format("当前任务[%s]下没有关联测试套件",taskEntity.getName());
        }
        return null;
    }

    /**
     * 校验任务名称是否存在
     * @param name
     * @param projectId
     * @return
     */
    public String checkTaskName(String name, Integer projectId) {
        LambdaQueryWrapper<TaskEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskEntity::getName, name);
        lambdaQueryWrapper.eq(TaskEntity::getProjectId, projectId);
        lambdaQueryWrapper.eq(TaskEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TaskEntity taskEntity = this.getOne(lambdaQueryWrapper, true);
        if (!Objects.isNull(taskEntity))
            return String.format("任务名称[%s]已存在",taskEntity.getName());
        return null;
    }

    /**
     * 校验任务是否存在测试运行记录
     * @param taskId
     * @return
     */
    public String checkTestRecord(Integer taskId) {
        LambdaQueryWrapper<TestRecordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestRecordEntity::getTaskId, taskId);
        lambdaQueryWrapper.eq(TestRecordEntity::getIsDelete, false);
        List<TestRecordEntity> testRecordEntities = testRecordService.list(lambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(testRecordEntities))
            return "测试任务已存在运行记录，不能删除";
        return null;
    }

    /**
     * 校验任务是否存在测试运行记录
     * @param taskId
     * @return
     */
    public String checkIsJob(Integer taskId) {
        LambdaQueryWrapper<JobEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(JobEntity::getTaskId, taskId);
        lambdaQueryWrapper.eq(JobEntity::getStatus, JobStatusEnum.START.getStatus());
        lambdaQueryWrapper.eq(JobEntity::getIsDelete, false);
        List<JobEntity> jobEntities = jobService.list(lambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(jobEntities))
            return "测试任务存在运行中的定时任务，请先停止";
        return null;
    }

    /**
     * 校验 关联模块是否存在
     * @param moduleIds
     * @return
     */
    private String checkModuleIds(List<Integer> moduleIds) {
        // 关联模块是否存在
        LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(ModuleEntity::getId, moduleIds);
        lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
        List<ModuleEntity> existModuleEntities = moduleService.list(lambdaQueryWrapper);
        List<Integer> notExistModuleIds = moduleIds.stream().filter(s -> existModuleEntities.stream().anyMatch(t -> t.getId() == s) == false).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(notExistModuleIds))
            return "关联模块[" + notExistModuleIds.stream().map(s -> s.toString()).collect(Collectors.joining(",")) + "]不存在";
        return null;
    }

    /**
     * 校验 是否删除了已存在的关联的task_module的模块id
     * @param inputDto
     * @return
     */
    public String checkTaskModuleIds(TaskUpdateInputDto inputDto) {
        LambdaQueryWrapper<TaskModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TaskModuleEntity::getTaskId, inputDto.getId());
        List<TaskModuleEntity> entities = taskModuleService.list(lambdaQueryWrapper);
        if (CollectionUtils.isNotEmpty(entities)) {
           if(entities.stream().filter(s->inputDto.getModuleIds().contains(s.getModuleId())==false).count()>0)
               return "关联模块["+ entities.stream().filter(s->inputDto.getModuleIds().contains(s.getModuleId())==false).map(s->s.getModuleId().toString()).collect(Collectors.joining(","))+ "]已经存在于任务[" + inputDto.getName() + "]中，不能删除" ;
        }
        return null;
    }



}
