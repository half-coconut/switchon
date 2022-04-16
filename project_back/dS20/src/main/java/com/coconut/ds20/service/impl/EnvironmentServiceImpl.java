package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.environment.EnvironmentCreateInputDto;
import com.coconut.ds20.dto.input.environment.EnvironmentUpdateInputDto;
import com.coconut.ds20.dto.output.environment.EnvironmentOutputDto;
import com.coconut.ds20.entity.EnvironmentEntity;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.EnvironmentMapper;
import com.coconut.ds20.service.EnvironmentService;
import com.coconut.ds20.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 1:01
 * File: EnvironmentServiceImpl
 * Project: dS20
 */

@Service
public class EnvironmentServiceImpl extends ServiceImpl<EnvironmentMapper, EnvironmentEntity> implements EnvironmentService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProjectService projectService;

    @Override
    public ResponseData<List<EnvironmentOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<EnvironmentOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目是否为空
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (projectId != null)
                projectLambdaQueryWrapper.eq(ProjectEntity::getId, projectId);
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ProjectEntity projectEntity = projectService.getOne(projectLambdaQueryWrapper, true);
            if (projectEntity == null)
                checkMsg.add("所属项目不存在");
            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<EnvironmentEntity> envLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (projectId != null)
                envLambdaQueryWrapper.eq(EnvironmentEntity::getProjectId, projectId);
            envLambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
            envLambdaQueryWrapper.orderByDesc(EnvironmentEntity::getId);
            List<EnvironmentEntity> entities = this.list(envLambdaQueryWrapper);
            List<EnvironmentOutputDto> outputDtos = entities.stream().map(s -> modelMapper.map(s, EnvironmentOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<EnvironmentOutputDto> getById(Integer id) {
        ResponseData<EnvironmentOutputDto> responseData;
        try {
            LambdaQueryWrapper<EnvironmentEntity> envLambdaQueryWrapper = new LambdaQueryWrapper<>();
            envLambdaQueryWrapper.eq(EnvironmentEntity::getId, id);
            envLambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
            EnvironmentEntity environment = this.getOne(envLambdaQueryWrapper);
            List<String> checkMsg = new ArrayList<>();
            if (environment == null)
                checkMsg.add("环境ID不存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            EnvironmentEntity entity = super.getById(id);
            EnvironmentOutputDto outputDto = modelMapper.map(entity, EnvironmentOutputDto.class);
            responseData = ResponseData.success(outputDto);

        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<EnvironmentOutputDto> create(EnvironmentCreateInputDto inputDto) {
        ResponseData<EnvironmentOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目是否为空
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectLambdaQueryWrapper.eq(ProjectEntity::getId, inputDto.getProjectId());
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ProjectEntity projectEntity = projectService.getOne(projectLambdaQueryWrapper, true);
            if (projectEntity == null)
                checkMsg.add("所属项目不存在");
            // 环境名称是否存在
            LambdaQueryWrapper<EnvironmentEntity> envLambdaQueryWrapper = new LambdaQueryWrapper<>();
            envLambdaQueryWrapper.eq(EnvironmentEntity::getName,inputDto.getName());
            envLambdaQueryWrapper.eq(EnvironmentEntity::getProjectId, inputDto.getProjectId());
            envLambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
            EnvironmentEntity entity = this.getOne(envLambdaQueryWrapper,true);
            if (entity != null)
                checkMsg.add("环境名称已存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // 设置为启用
            EnvironmentEntity environmentEntity = modelMapper.map(inputDto,EnvironmentEntity.class);
            environmentEntity.setStatus(StatusEnum.ENABLE.getStatus());
            this.save(environmentEntity);
            EnvironmentOutputDto outputDto = modelMapper.map(environmentEntity,EnvironmentOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<EnvironmentOutputDto> update(EnvironmentUpdateInputDto inputDto) {
        ResponseData<EnvironmentOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            LambdaQueryWrapper<EnvironmentEntity> envLambdaQueryWrapper = new LambdaQueryWrapper<>();
            envLambdaQueryWrapper.eq(EnvironmentEntity::getId, inputDto.getId());
            envLambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
            EnvironmentEntity environment = this.getOne(envLambdaQueryWrapper);
            if (environment == null)
                checkMsg.add("环境ID不存在");
            // 所属项目是否为空
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectLambdaQueryWrapper.eq(ProjectEntity::getId, inputDto.getProjectId());
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ProjectEntity projectEntity = projectService.getOne(projectLambdaQueryWrapper, true);
            if (projectEntity == null)
                checkMsg.add("所属项目不存在");
            // 环境名称是否存在
            envLambdaQueryWrapper = new LambdaQueryWrapper<>();
            envLambdaQueryWrapper.eq(EnvironmentEntity::getName,inputDto.getName());
            envLambdaQueryWrapper.eq(EnvironmentEntity::getProjectId, inputDto.getProjectId());
            envLambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
            EnvironmentEntity entity = this.getOne(envLambdaQueryWrapper);
            if (entity != null)
                checkMsg.add("环境名称已存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // 设置为启用
            EnvironmentEntity environmentEntity = modelMapper.map(inputDto,EnvironmentEntity.class);
            environmentEntity.setStatus(StatusEnum.ENABLE.getStatus());
            this.updateById(environmentEntity);
            EnvironmentOutputDto outputDto = modelMapper.map(environmentEntity,EnvironmentOutputDto.class);
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
            LambdaQueryWrapper<EnvironmentEntity> envLambdaQueryWrapper = new LambdaQueryWrapper<>();
            envLambdaQueryWrapper.eq(EnvironmentEntity::getId, id);
            envLambdaQueryWrapper.eq(EnvironmentEntity::getStatus, StatusEnum.ENABLE.getStatus());
            EnvironmentEntity environmentEntity = this.getOne(envLambdaQueryWrapper);
            List<String> checkMsg = new ArrayList<>();
            if (environmentEntity == null)
                checkMsg.add("环境ID不存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            environmentEntity.setStatus(StatusEnum.DELETE.getStatus());
            Boolean result = this.updateById(environmentEntity);
            responseData = ResponseData.success(result);

        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }
}
