package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.module.ModuleCreateInputDto;
import com.coconut.ds20.dto.input.module.ModuleUpdateInputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.entity.ModuleEntity;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.ModuleMapper;
import com.coconut.ds20.service.ModuleService;
import com.coconut.ds20.service.ProjectService;
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
 * Date: 2022/4/5 23:19
 * File: ModuleServiceImpl
 * Project: dS20
 */

@Service
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, ModuleEntity> implements ModuleService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProjectService projectService;

    @Override
    public ResponseData<List<ModuleOutputDto>> query(Integer pageSize, Integer pageIndex, Integer projectId) {
        ResponseData<List<ModuleOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(ProjectEntity::getId, projectId);
            lambdaQueryWrapper1.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(lambdaQueryWrapper1);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在。");
            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            IPage<ModuleEntity> queryPage = new Page<>(pageIndex, pageSize);
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ModuleEntity::getProjectId, projectId);
            lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            queryPage = this.page(queryPage, lambdaQueryWrapper);
            responseData = ResponseData.success(queryPage.getRecords().stream().map(s -> modelMapper.map(s, ModuleOutputDto.class)).collect(Collectors.toList()));
            responseData.setTotal(queryPage.getTotal());

        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<List<ModuleOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<ModuleOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目ID是否存在
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            if (projectId != null)
                lambdaQueryWrapper1.eq(ProjectEntity::getId, projectId);
            lambdaQueryWrapper1.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(lambdaQueryWrapper1);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在。");
            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ModuleEntity::getProjectId, projectId);
            lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            List<ModuleEntity> entities = this.list(lambdaQueryWrapper);
            List<ModuleOutputDto> outputDtos = entities.stream().map(s -> modelMapper.map(s, ModuleOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());

        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<ModuleOutputDto> getById(Integer id) {
        ResponseData<ModuleOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // ID是否存在
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ModuleEntity::getId, id);
            lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ModuleEntity entity = this.getOne(lambdaQueryWrapper, true);
            if (Objects.isNull(entity))
                checkMsg.add("模块ID不存在");
            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }

            ModuleEntity entity1 = super.getById(id);
            ModuleOutputDto outputDto = modelMapper.map(entity1, ModuleOutputDto.class);
            responseData = ResponseData.success(outputDto);

        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<ModuleOutputDto> create(ModuleCreateInputDto inputDto) {
        ResponseData<ModuleOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 模块名称是否存在
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ModuleEntity::getName, inputDto.getName());
            lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.eq(ModuleEntity::getProjectId, inputDto.getProjectId());
            ModuleEntity moduleEntity = this.getOne(lambdaQueryWrapper, true);
            if (moduleEntity != null)
                checkMsg.add("模块名称已存在");
            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(ProjectEntity::getId, inputDto.getProjectId());
            lambdaQueryWrapper1.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(lambdaQueryWrapper1);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在。");
            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // DTO 转 Entity
            ModuleEntity entity = modelMapper.map(inputDto, ModuleEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.save(entity);
            ModuleOutputDto outputDto = modelMapper.map(entity, ModuleOutputDto.class);
            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<ModuleOutputDto> update(ModuleUpdateInputDto inputDto) {
        ResponseData<ModuleOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 判断ID是否存在
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper1.eq(ModuleEntity::getId, inputDto.getId());
            lambdaQueryWrapper1.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ModuleEntity moduleEntity1 = this.getOne(lambdaQueryWrapper1, true);
            if (moduleEntity1 == null)
                checkMsg.add("模块ID不存在");

            // 模块id是否存在，名称是否存在
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ModuleEntity::getId, inputDto.getId());
            lambdaQueryWrapper.eq(ModuleEntity::getName, inputDto.getName());
            lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            lambdaQueryWrapper.eq(ModuleEntity::getProjectId, inputDto.getProjectId());
            ModuleEntity moduleEntity = this.getOne(lambdaQueryWrapper, true);
            if (moduleEntity != null)
                checkMsg.add("模块名称已存在");

            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper3.eq(ProjectEntity::getId, inputDto.getProjectId());
            lambdaQueryWrapper3.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(lambdaQueryWrapper3);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在。");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // DTO 转 Entity
            ModuleEntity entity = modelMapper.map(inputDto, ModuleEntity.class);
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.updateById(entity);
            ModuleOutputDto outputDto = modelMapper.map(entity, ModuleOutputDto.class);
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
            // 判断ID是否存在
            LambdaQueryWrapper<ModuleEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ModuleEntity::getId, id);
            lambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ModuleEntity moduleEntity = this.getOne(lambdaQueryWrapper, true);
            if (moduleEntity == null)
                checkMsg.add("模块ID不存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            ModuleEntity entity = super.getById(id);
            // 删除这条数据
            entity.setStatus(StatusEnum.DELETE.getStatus());
            Boolean result = this.updateById(entity);
            responseData = ResponseData.success(result);

        } catch (Exception ex) {
            log.error("操作异常：", ex);
            responseData = ResponseData.failure("操作异常：" + ex.toString());
        }

        return responseData;
    }
}
