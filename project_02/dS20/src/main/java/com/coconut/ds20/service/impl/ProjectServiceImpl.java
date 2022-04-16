package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.project.ProjectCreateInputDto;
import com.coconut.ds20.dto.input.project.ProjectUpdateInputDto;
import com.coconut.ds20.dto.output.project.ProjectOutputDto;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.ProjectMapper;
import com.coconut.ds20.service.ProjectService;
import com.coconut.ds20.util.SessionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:29
 * File: ProjectServiceImpl
 * Project: dS20
 */

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, ProjectEntity> implements ProjectService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SessionUtil sessionUtil;

    @Override
    public ResponseData<List<ProjectOutputDto>> queryAll() {
        ResponseData<List<ProjectOutputDto>> responseData;
        try {
            // 只获取未删除的项目
            List<ProjectEntity> entities = this.list();
            entities = entities.stream().filter(s->s.getStatus().equals(StatusEnum.ENABLE.getStatus())).collect(Collectors.toList());
            // 排序后转换成dto输出
            List<ProjectOutputDto> outputDtos = entities.stream().sorted(Comparator.comparing(ProjectEntity::getId).reversed()).map(s->modelMapper.map(s,ProjectOutputDto.class)).collect(Collectors.toList());

            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<ProjectOutputDto> getById(Integer id) {
        ResponseData<ProjectOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 判断id是否存在
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ProjectEntity::getId,id);
            lambdaQueryWrapper.eq(ProjectEntity::getStatus,StatusEnum.ENABLE.getStatus());
            ProjectEntity projectEntity = this.getOne(lambdaQueryWrapper,true);
            if (projectEntity == null){
                checkMsg.add("ID不存在！");
            }
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            ProjectEntity entity = super.getById(id);
            ProjectOutputDto outputDto = modelMapper.map(entity, ProjectOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<ProjectOutputDto> create(ProjectCreateInputDto inputDto) {
        ResponseData<ProjectOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 项目名称不能重复
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ProjectEntity::getName,inputDto.getName());
            lambdaQueryWrapper.eq(ProjectEntity::getStatus,StatusEnum.ENABLE);
            ProjectEntity projectEntity = this.getOne(lambdaQueryWrapper,true);
            if (projectEntity != null){
                checkMsg.add("项目已存在");
            }
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // dto 转 entity
            ProjectEntity entity = modelMapper.map(inputDto,ProjectEntity.class);
            // 设置默认值
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            if (entity.getOwnerId() == null)
                entity.setOwnerId(sessionUtil.getCurrentUser().getUserEntity().getId());
            if (entity.getOwnerName() == null)
                entity.setOwnerName(sessionUtil.getCurrentUser().getUserEntity().getUsername());
            // 新增一条
            this.save(entity);
            // entity 转 dto
            ProjectOutputDto outputDto = modelMapper.map(entity,ProjectOutputDto.class);
            responseData = ResponseData.success(outputDto);

        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<ProjectOutputDto> update(ProjectUpdateInputDto inputDto) {
        ResponseData<ProjectOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 项目id是存在的，且状态是启动的，名称不能重复
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ProjectEntity::getId,inputDto.getId());
            lambdaQueryWrapper.eq(ProjectEntity::getName,inputDto.getName());
            lambdaQueryWrapper.eq(ProjectEntity::getStatus,StatusEnum.ENABLE);
            ProjectEntity projectEntity = this.getOne(lambdaQueryWrapper,true);
            if (projectEntity != null){
                checkMsg.add("项目名称已存在");
            }
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // dto 转 entity
            ProjectEntity entity = modelMapper.map(inputDto,ProjectEntity.class);
            // 更新一条
            this.updateById(entity);
            // entity 转 dto
            ProjectOutputDto outputDto = modelMapper.map(entity,ProjectOutputDto.class);
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
            // 判断id是否存在
            LambdaQueryWrapper<ProjectEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ProjectEntity::getId,id);
            lambdaQueryWrapper.eq(ProjectEntity::getStatus,StatusEnum.ENABLE.getStatus());
            ProjectEntity projectEntity = this.getOne(lambdaQueryWrapper,true);
            if (projectEntity == null){
                checkMsg.add("ID不存在！");
            }
            if (checkMsg.size() >0){
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            ProjectEntity entity = super.getById(id);
            // 删除这条
            entity.setStatus(StatusEnum.DELETE.getStatus());
            Boolean result = this.updateById(entity);
            responseData = ResponseData.success(result);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }
}
