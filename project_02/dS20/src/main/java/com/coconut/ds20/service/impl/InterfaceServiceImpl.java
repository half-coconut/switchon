package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.inf.InterfaceCreateInputDto;
import com.coconut.ds20.dto.input.inf.InterfaceUpdateInputDto;
import com.coconut.ds20.dto.output.inf.InterfaceOutputDto;
import com.coconut.ds20.dto.output.inf.InterfaceQueryOutputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.entity.InterfaceEntity;
import com.coconut.ds20.entity.ModuleEntity;
import com.coconut.ds20.entity.ProjectEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.InterfaceMapper;
import com.coconut.ds20.service.InterfaceService;
import com.coconut.ds20.service.ModuleService;
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
 * Date: 2022/4/5 22:49
 * File: InterfaceServiceImpl
 * Project: dS20
 */

@Service
public class InterfaceServiceImpl extends ServiceImpl<InterfaceMapper, InterfaceEntity> implements InterfaceService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProjectService projectService;

    @Autowired
    ModuleService moduleService;

    @Override
    public ResponseData<List<InterfaceQueryOutputDto>> query(Integer pageSize, Integer pageIndex, Integer moduleId, Integer projectId) {
        ResponseData<List<InterfaceQueryOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (projectId != null)
                projectLambdaQueryWrapper.eq(ProjectEntity::getId, projectId);
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(projectLambdaQueryWrapper);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在");
            // 所属模块是否存在，模块非必填
            if (moduleId != null){
                LambdaQueryWrapper<ModuleEntity> moduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                moduleLambdaQueryWrapper.eq(ModuleEntity::getId, moduleId);
                moduleLambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
                ModuleEntity moduleEntity = moduleService.getOne(moduleLambdaQueryWrapper,true);
                if (moduleEntity == null)
                    checkMsg.add("所属模块不存在");
            }
            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            IPage<InterfaceEntity> queryPage = new Page<>(pageIndex,pageSize);
            LambdaQueryWrapper<InterfaceEntity> infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (moduleId != null)
                infLambdaQueryWrapper.eq(InterfaceEntity::getModuleId,moduleId);
            infLambdaQueryWrapper.eq(InterfaceEntity::getProjectId,projectId);
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            infLambdaQueryWrapper.orderByDesc(InterfaceEntity::getId);
            queryPage = this.page(queryPage,infLambdaQueryWrapper);
            List<InterfaceQueryOutputDto> outputDtos = queryPage.getRecords().stream().map(s->modelMapper.map(s,InterfaceQueryOutputDto.class)).collect(Collectors.toList());

            // 获取关联模块
             List<Integer> modulesId = outputDtos.stream().map(s->s.getModuleId()).collect(Collectors.toList());
             List<ModuleEntity> moduleEntities = moduleService.listByIds(modulesId);
             outputDtos.forEach(s->{
                 ModuleEntity moduleEntity1 = moduleEntities.stream().filter(t->t.getId().equals(s.getModuleId())).findFirst().orElse(null);
                 if (moduleEntity1 != null)
                     s.setModule(modelMapper.map(moduleEntity1, ModuleOutputDto.class));
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
    public ResponseData<List<InterfaceOutputDto>> queryByProjectId(Integer projectId) {
        ResponseData<List<InterfaceOutputDto>> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (projectId != null)
                projectLambdaQueryWrapper.eq(ProjectEntity::getId, projectId);
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(projectLambdaQueryWrapper);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            LambdaQueryWrapper<InterfaceEntity> infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (projectId != null)
                infLambdaQueryWrapper.eq(InterfaceEntity::getProjectId,projectId);
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            List<InterfaceEntity> entities = this.list(infLambdaQueryWrapper);
            List<InterfaceOutputDto> outputDtos = entities.stream().map(s->modelMapper.map(s,InterfaceOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<InterfaceOutputDto> getById(Integer id) {
        ResponseData<InterfaceOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 接口id是否存在
            LambdaQueryWrapper<InterfaceEntity> infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (id != null)
                infLambdaQueryWrapper.eq(InterfaceEntity::getId,id);
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            InterfaceEntity interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity == null)
                checkMsg.add("接口ID不存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            InterfaceEntity entity = super.getById(id);
            InterfaceOutputDto outputDto = modelMapper.map(entity,InterfaceOutputDto.class);
            responseData = ResponseData.success(outputDto);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<InterfaceOutputDto> create(InterfaceCreateInputDto inputDto) {
        ResponseData<InterfaceOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectLambdaQueryWrapper.eq(ProjectEntity::getId, inputDto.getProjectId());
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(projectLambdaQueryWrapper);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在");
            // 所属模块是否存在
            LambdaQueryWrapper<ModuleEntity> moduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            moduleLambdaQueryWrapper.eq(ModuleEntity::getId, inputDto.getModuleId());
            moduleLambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ModuleEntity moduleEntity = moduleService.getOne(moduleLambdaQueryWrapper, true);
            if (moduleEntity == null)
                checkMsg.add("所属模块不存在");
            // 接口名称是否存在
            LambdaQueryWrapper<InterfaceEntity> infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            infLambdaQueryWrapper.eq(InterfaceEntity::getName,inputDto.getName());
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            infLambdaQueryWrapper.eq(InterfaceEntity::getModuleId,inputDto.getModuleId());
            infLambdaQueryWrapper.eq(InterfaceEntity::getProjectId,inputDto.getProjectId());
            InterfaceEntity interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity != null)
                checkMsg.add("接口名称已经存在");

            // 接口地址是否存在
            infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            infLambdaQueryWrapper.eq(InterfaceEntity::getPath,inputDto.getPath());
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            infLambdaQueryWrapper.eq(InterfaceEntity::getModuleId,inputDto.getModuleId());
            infLambdaQueryWrapper.eq(InterfaceEntity::getProjectId,inputDto.getProjectId());
            interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity != null)
                checkMsg.add("接口地址已经存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            InterfaceEntity entity = modelMapper.map(inputDto,InterfaceEntity.class);
            // 设置默认值，启用
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.save(entity);
            InterfaceOutputDto outputDto = modelMapper.map(entity,InterfaceOutputDto.class);
            responseData = ResponseData.success(outputDto);

        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<InterfaceOutputDto> update(InterfaceUpdateInputDto inputDto) {
        ResponseData<InterfaceOutputDto> responseData;
        try {
            List<String> checkMsg = new ArrayList<>();
            // 接口id是否存在
            LambdaQueryWrapper<InterfaceEntity> infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            infLambdaQueryWrapper.eq(InterfaceEntity::getId,inputDto.getId());
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            InterfaceEntity interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity == null)
                checkMsg.add("接口ID不存在");
            // 所属项目是否存在
            LambdaQueryWrapper<ProjectEntity> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
            projectLambdaQueryWrapper.eq(ProjectEntity::getId, inputDto.getProjectId());
            projectLambdaQueryWrapper.eq(ProjectEntity::getStatus, StatusEnum.ENABLE.getStatus());
            long existProject = projectService.count(projectLambdaQueryWrapper);
            if (existProject <= 0)
                checkMsg.add("所属项目不存在");
            // 所属模块是否存在
            LambdaQueryWrapper<ModuleEntity> moduleLambdaQueryWrapper = new LambdaQueryWrapper<>();
            moduleLambdaQueryWrapper.eq(ModuleEntity::getId, inputDto.getModuleId());
            moduleLambdaQueryWrapper.eq(ModuleEntity::getStatus, StatusEnum.ENABLE.getStatus());
            ModuleEntity moduleEntity = moduleService.getOne(moduleLambdaQueryWrapper, true);
            if (moduleEntity == null)
                checkMsg.add("所属模块不存在");
            // 接口名称是否存在
            infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            infLambdaQueryWrapper.eq(InterfaceEntity::getName,inputDto.getName());
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            infLambdaQueryWrapper.eq(InterfaceEntity::getModuleId,inputDto.getModuleId());
            infLambdaQueryWrapper.eq(InterfaceEntity::getProjectId,inputDto.getProjectId());
            interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity != null)
                checkMsg.add("接口名称已经存在");

            // 接口地址是否存在
            infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            infLambdaQueryWrapper.eq(InterfaceEntity::getPath,inputDto.getPath());
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            infLambdaQueryWrapper.eq(InterfaceEntity::getModuleId,inputDto.getModuleId());
            infLambdaQueryWrapper.eq(InterfaceEntity::getProjectId,inputDto.getProjectId());
            interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity != null)
                checkMsg.add("接口地址已经存在");

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            InterfaceEntity entity = modelMapper.map(inputDto,InterfaceEntity.class);
            // 设置默认值，启用
            entity.setStatus(StatusEnum.ENABLE.getStatus());
            this.updateById(entity);
            InterfaceOutputDto outputDto = modelMapper.map(entity,InterfaceOutputDto.class);
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
            // 接口id是否存在
            LambdaQueryWrapper<InterfaceEntity> infLambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (id != null)
                infLambdaQueryWrapper.eq(InterfaceEntity::getId,id);
            infLambdaQueryWrapper.eq(InterfaceEntity::getStatus,StatusEnum.ENABLE.getStatus());
            InterfaceEntity interfaceEntity = this.getOne(infLambdaQueryWrapper,true);
            if (interfaceEntity == null)
                checkMsg.add("接口ID不存在");
            // TODO: 接口被测试套件的用例引用，不能删除

            if (checkMsg.size() > 0) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(checkMsg.stream().collect(Collectors.joining(",")));

                return responseData;
            }
            // 修改状态为 删除
            interfaceEntity.setStatus(StatusEnum.DELETE.getStatus());
            Boolean result = this.updateById(interfaceEntity);
            responseData = ResponseData.success(result);
        }catch (Exception ex){
            log.error("操作异常：",ex);
            responseData = ResponseData.failure("操作异常："+ex.toString());
        }
        return responseData;
    }
}
