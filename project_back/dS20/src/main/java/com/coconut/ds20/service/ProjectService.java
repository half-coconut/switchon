package com.coconut.ds20.service;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:07
 * File: ProjectService
 * Project: dS20
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.project.ProjectCreateInputDto;
import com.coconut.ds20.dto.input.project.ProjectUpdateInputDto;
import com.coconut.ds20.dto.output.project.ProjectOutputDto;
import com.coconut.ds20.entity.ProjectEntity;

import java.util.List;

/**
 * 项目服务
 */
public interface ProjectService extends IService<ProjectEntity> {
    ResponseData<List<ProjectOutputDto>> queryAll();
    ResponseData<ProjectOutputDto> getById(Integer id);
    ResponseData<ProjectOutputDto> create(ProjectCreateInputDto inputDto);
    ResponseData<ProjectOutputDto> update(ProjectUpdateInputDto inputDto);
    ResponseData<Boolean> delete(Integer id);
}
