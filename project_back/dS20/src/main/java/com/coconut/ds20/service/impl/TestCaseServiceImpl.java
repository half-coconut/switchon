package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testcase.TestCaseCreateInputDto;
import com.coconut.ds20.dto.input.testcase.TestCaseUpdateInputDto;
import com.coconut.ds20.dto.output.testcase.TestCaseOutputDto;
import com.coconut.ds20.entity.InterfaceEntity;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.entity.TestCaseEntity;
import com.coconut.ds20.entity.TestSuiteEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.TestCaseMapper;
import com.coconut.ds20.service.InterfaceService;
import com.coconut.ds20.service.ProjectService;
import com.coconut.ds20.service.TestCaseService;
import com.coconut.ds20.service.TestSuiteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/10 16:47
 * File: TestCaseServiceImpl
 * Project: dS20
 */

@Service
public class TestCaseServiceImpl extends ServiceImpl<TestCaseMapper, TestCaseEntity> implements TestCaseService {
    @Autowired
    TestSuiteService testSuiteService;

    @Autowired
    InterfaceService interfaceService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseData<List<TestCaseOutputDto>> query(Integer pageIndex, Integer pageSize, Integer interfaceId, Integer testSuiteId, Integer taskId, Integer projectId) {
        ResponseData<List<TestCaseOutputDto>> responseData;
        try{
            LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (!Objects.isNull(interfaceId))
                lambdaQueryWrapper.eq(TestCaseEntity::getInterfaceId,interfaceId);
            if (!Objects.isNull(testSuiteId))
                lambdaQueryWrapper.eq(TestCaseEntity::getTestSuiteId,testSuiteId);
            if (!Objects.isNull(taskId))
                lambdaQueryWrapper.eq(TestCaseEntity::getTaskId,taskId);
            if (!Objects.isNull(projectId))
                lambdaQueryWrapper.eq(TestCaseEntity::getProjectId,projectId);
            lambdaQueryWrapper.orderByDesc(TestCaseEntity::getId);
            IPage<TestCaseEntity> querypage = new Page(pageIndex,pageSize);
            querypage = this.page(querypage,lambdaQueryWrapper);
            List<TestCaseOutputDto> outputDtos = querypage.getRecords().stream().map(s->modelMapper.map(s,TestCaseOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos);
            responseData.setTotal(querypage.getTotal());

        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestCaseOutputDto> getById(Integer id) {
        ResponseData<TestCaseOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTestCaseId(id)))
                checkMsg.add(checkTestCaseId(id));

            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestCaseEntity entity = super.getById(id);
            TestCaseOutputDto outputDto = modelMapper.map(entity, TestCaseOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<TestCaseOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<TestCaseOutputDto>> responseData;
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
            LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestCaseEntity::getProjectId,projectId);
            lambdaQueryWrapper.eq(TestCaseEntity::getStatus,StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.orderByDesc(TestCaseEntity::getId);
            List<TestCaseEntity> entities = this.list(lambdaQueryWrapper);
            List<TestCaseOutputDto> outputDtos = entities.stream().map(s->modelMapper.map(s,TestCaseOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestCaseOutputDto> create(TestCaseCreateInputDto inputDto) {
        ResponseData<TestCaseOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTestSuiteId(inputDto.getTestSuiteId())))
                checkMsg.add(checkTestSuiteId(inputDto.getTestSuiteId()));
            if (!Objects.isNull(checkInterfaceId(inputDto.getInterfaceId())))
                checkMsg.add(checkInterfaceId(inputDto.getInterfaceId()));
            if (!Objects.isNull(checkTestCaseName(inputDto)))
                checkMsg.add(checkTestCaseName(inputDto));
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            TestCaseEntity entity = modelMapper.map(inputDto,TestCaseEntity.class);
            // 设置默认值
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            if(Objects.isNull(entity.getRequestData())){
                entity.setRequestData("{\"headers\":{},\"params\":{},\"data\":{},\"json\":{}}");
            }
            if(Objects.isNull(entity.getExtract())){
                entity.setExtract("[]");
            }
            if(Objects.isNull(entity.getAssertion())){
                entity.setAssertion("[]");
            }
            if(Objects.isNull(entity.getDbAssertion())){
                entity.setDbAssertion("[]");
            }
            if(Objects.isNull(entity.getOrderIndex())){
                entity.setOrderIndex(10);
            }

            this.save(entity);
            TestCaseOutputDto outputDto = modelMapper.map(entity, TestCaseOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestCaseOutputDto> update(TestCaseUpdateInputDto inputDto) {
        ResponseData<TestCaseOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTestSuiteId(inputDto.getTestSuiteId())))
                checkMsg.add(checkTestSuiteId(inputDto.getTestSuiteId()));
            if (!Objects.isNull(checkInterfaceId(inputDto.getInterfaceId())))
                checkMsg.add(checkInterfaceId(inputDto.getInterfaceId()));
            if (!Objects.isNull(checkTestCaseNameForUpdate(inputDto)))
                checkMsg.add(checkTestCaseNameForUpdate(inputDto));
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            TestCaseEntity entity = modelMapper.map(inputDto,TestCaseEntity.class);

            this.updateById(entity);
            TestCaseOutputDto outputDto = modelMapper.map(entity, TestCaseOutputDto.class);
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
            if (!Objects.isNull(checkTestCaseId(id)))
                checkMsg.add(checkTestCaseId(id));

            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestCaseEntity::getId,id);
            lambdaQueryWrapper.eq(TestCaseEntity::getStatus,StatusEnum.ENABLE.getStatus());
            TestCaseEntity testCaseEntity = this.getOne(lambdaQueryWrapper,true);
            testCaseEntity.setStatus(StatusEnum.DELETE.getStatus());

            Boolean result = this.updateById(testCaseEntity);
            responseData = ResponseData.success(result);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestCaseOutputDto> copy(TestCaseCreateInputDto inputDto) {
        ResponseData<TestCaseOutputDto> responseData;
        try {
            // 1.添加“-copy”
            inputDto.setName(inputDto.getName() + "_copy");
            // 2.添加查询到 多条相同名称时，取最长的，添加“-copy”
            LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestCaseEntity::getName,inputDto.getName());
            lambdaQueryWrapper.eq(TestCaseEntity::getInterfaceId, inputDto.getInterfaceId());
            lambdaQueryWrapper.eq(TestCaseEntity::getTestSuiteId, inputDto.getTestSuiteId());
            lambdaQueryWrapper.eq(TestCaseEntity::getStatus, StatusEnum.ENABLE.getStatus());
            List<TestCaseEntity> testCaseEntities = this.list(lambdaQueryWrapper);
            if (!CollectionUtils.isEmpty(testCaseEntities)){
               TestCaseEntity testCaseEntity = testCaseEntities.stream().max((x,y)->{
                    if (x.getName().length() > y.getName().length()){
                        return 1;
                    }else {
                        return -1;
                    }
                }).get();
                inputDto.setName(testCaseEntity.getName() + "_copy");
            }

            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTestSuiteId(inputDto.getTestSuiteId())))
                checkMsg.add(checkTestSuiteId(inputDto.getTestSuiteId()));
            if (!Objects.isNull(checkInterfaceId(inputDto.getInterfaceId())))
                checkMsg.add(checkInterfaceId(inputDto.getInterfaceId()));
            if (!Objects.isNull(checkTestCaseName(inputDto)))
                checkMsg.add(checkTestCaseName(inputDto));
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            TestCaseEntity entity = modelMapper.map(inputDto,TestCaseEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.save(entity);
            TestCaseOutputDto outputDto = modelMapper.map(entity, TestCaseOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    /**
     * 校验测试用例的id是否存在
     * @param id
     * @return
     */
    private String checkTestCaseId(Integer id){
        LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestCaseEntity::getId,id);
        lambdaQueryWrapper.eq(TestCaseEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TestCaseEntity testCaseEntity = this.getOne(lambdaQueryWrapper,true);
        if (Objects.isNull(testCaseEntity))
            return "测试用例ID不存在";
        return null;
    }
    /**
     * 校验测试用例的name是否存在
     * @param inputDto
     * @return
     */
    private String checkTestCaseName(TestCaseCreateInputDto inputDto){
        LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestCaseEntity::getName,inputDto.getName());
        lambdaQueryWrapper.eq(TestCaseEntity::getInterfaceId, inputDto.getInterfaceId());
        lambdaQueryWrapper.eq(TestCaseEntity::getTestSuiteId, inputDto.getTestSuiteId());
        lambdaQueryWrapper.eq(TestCaseEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TestCaseEntity testCaseEntity = this.getOne(lambdaQueryWrapper,true);
        if (!Objects.isNull(testCaseEntity))
            return "测试用例名称已存在";
        return null;
    }

    /**
     * 校验测试用例的name是否存在
     * @param inputDto
     * @return
     */
    private String checkTestCaseNameForUpdate(TestCaseUpdateInputDto inputDto){
        LambdaQueryWrapper<TestCaseEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestCaseEntity::getId,inputDto.getId());
        lambdaQueryWrapper.eq(TestCaseEntity::getName,inputDto.getName());
        lambdaQueryWrapper.eq(TestCaseEntity::getInterfaceId, inputDto.getInterfaceId());
        lambdaQueryWrapper.eq(TestCaseEntity::getTestSuiteId, inputDto.getTestSuiteId());
        lambdaQueryWrapper.eq(TestCaseEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TestCaseEntity testCaseEntity = this.getOne(lambdaQueryWrapper,true);
        if (!Objects.isNull(testCaseEntity))
            return "测试用例名称已存在";
        return null;
    }

    /**
     * 校验测试套件的id是否存在
     * @param testSuiteId
     * @return
     */
    private String checkTestSuiteId(Integer testSuiteId){
        LambdaQueryWrapper<TestSuiteEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestSuiteEntity::getId,testSuiteId);
        lambdaQueryWrapper.eq(TestSuiteEntity::getStatus,StatusEnum.ENABLE.getStatus());
        TestSuiteEntity testSuiteEntity = testSuiteService.getOne(lambdaQueryWrapper,true);
        if (Objects.isNull(testSuiteEntity))
            return "所属测试套件不存在";
        return null;
    }

    /**
     * 校验接口是否存在
     * @param interfaceId
     * @return
     */
    private String checkInterfaceId(Integer interfaceId){
        LambdaQueryWrapper<InterfaceEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(InterfaceEntity::getId,interfaceId);
        lambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
        InterfaceEntity interfaceEntity = interfaceService.getOne(lambdaQueryWrapper,true);
        if (Objects.isNull(interfaceEntity))
            return "所属接口不存在";
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
}
