package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.testreport.TestReportCreateInputDto;
import com.coconut.ds20.dto.input.testreport.TestReportUpdateInputDto;
import com.coconut.ds20.dto.output.testreport.TestReportOutputDto;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.entity.TestRecordEntity;
import com.coconut.ds20.entity.TestReportEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.TestReportMapper;
import com.coconut.ds20.service.ProjectService;
import com.coconut.ds20.service.TestRecordService;
import com.coconut.ds20.service.TestReportService;
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
 * Date: 2022/4/10 22:30
 * File: TestReportServiceImpl
 * Project: dS20
 */

@Service
public class TestReportServiceImpl extends ServiceImpl<TestReportMapper, TestReportEntity> implements TestReportService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ProjectService projectService;
    @Autowired
    TestRecordService testRecordService;

    @Override
    public ResponseData<List<TestReportOutputDto>> query(Integer pageIndex, Integer pageSize, Integer testRecordId, Integer projectId) {
        ResponseData<List<TestReportOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(projectId)))
                checkMsg.add(checkProjectId(projectId));
            if (!Objects.isNull(checkTestRecordId(testRecordId)))
                checkMsg.add(checkTestRecordId(testRecordId));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<TestReportEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestReportEntity::getProjectId, projectId);
            lambdaQueryWrapper.eq(TestReportEntity::getTestRecordId, testRecordId);
            lambdaQueryWrapper.eq(TestReportEntity::getStatus, StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.orderByDesc(TestReportEntity::getId);
            IPage<TestReportEntity> queryPage = new Page<>(pageIndex,pageSize);
            queryPage = this.page(queryPage,lambdaQueryWrapper);
            List<TestReportOutputDto> outputDtos = queryPage.getRecords().stream().map(s -> modelMapper.map(s, TestReportOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos);
            responseData.setTotal(queryPage.getTotal());
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<TestReportOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<TestReportOutputDto>> responseData;
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
            LambdaQueryWrapper<TestReportEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(TestReportEntity::getProjectId, projectId);
            lambdaQueryWrapper.eq(TestReportEntity::getStatus, StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.orderByDesc(TestReportEntity::getId);
            List<TestReportEntity> entities = this.list(lambdaQueryWrapper);
            List<TestReportOutputDto> outputDtos = entities.stream().map(s -> modelMapper.map(s, TestReportOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestReportOutputDto> getById(Integer id) {
        ResponseData<TestReportOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkTestReportId(id)))
                checkMsg.add(checkTestReportId(id));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestReportEntity entity = super.getById(id);
            TestReportOutputDto outputDto = modelMapper.map(entity, TestReportOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestReportOutputDto> create(TestReportCreateInputDto inputDto) {
        ResponseData<TestReportOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTestRecordId(inputDto.getTestRecordId())))
                checkMsg.add(checkTestRecordId(inputDto.getTestRecordId()));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestReportEntity entity = modelMapper.map(inputDto, TestReportEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.save(entity);
            TestReportOutputDto outputDto = modelMapper.map(entity, TestReportOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<TestReportOutputDto> update(TestReportUpdateInputDto inputDto) {
        ResponseData<TestReportOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            if (!Objects.isNull(checkProjectId(inputDto.getProjectId())))
                checkMsg.add(checkProjectId(inputDto.getProjectId()));
            if (!Objects.isNull(checkTestRecordId(inputDto.getTestRecordId())))
                checkMsg.add(checkTestRecordId(inputDto.getTestRecordId()));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestReportEntity entity = modelMapper.map(inputDto, TestReportEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.updateById(entity);
            TestReportOutputDto outputDto = modelMapper.map(entity, TestReportOutputDto.class);
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
            if (!Objects.isNull(checkTestReportId(id)))
                checkMsg.add(checkTestReportId(id));

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            TestReportEntity entity = super.getById(id);
            entity.setStatus(StatusEnum.DELETE.getStatus());
            Boolean result = this.updateById(entity);
            responseData = ResponseData.success(result);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    /**
     * 校验 测试报告ID是否存在
     *
     * @param id
     * @return
     */
    public String checkTestReportId(Integer id) {
        LambdaQueryWrapper<TestReportEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestReportEntity::getId, id);
        lambdaQueryWrapper.eq(TestReportEntity::getStatus, StatusEnum.ENABLE.getStatus());
        TestReportEntity testReportEntity = this.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(testReportEntity))
            return "测试报告ID不存在";
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
     * 校验 运行记录是否存在
     *
     * @param testRecordId
     * @return
     */
    public String checkTestRecordId(Integer testRecordId) {
        LambdaQueryWrapper<TestRecordEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TestRecordEntity::getId, testRecordId);
        lambdaQueryWrapper.eq(TestRecordEntity::getIsDelete, false);
        TestRecordEntity testRecordEntity = testRecordService.getOne(lambdaQueryWrapper, true);
        if (Objects.isNull(testRecordEntity))
            return "所属运行记录不存在";
        return null;
    }
}
