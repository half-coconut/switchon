package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.environment.EnvironmentCreateInputDto;
import com.coconut.ds20.dto.input.environment.EnvironmentUpdateInputDto;
import com.coconut.ds20.dto.output.environment.EnvironmentOutputDto;
import com.coconut.ds20.entity.EnvironmentEntity;

import java.util.List;

public interface EnvironmentService extends IService<EnvironmentEntity> {
    /**
     * 查询全部
     * @param projectId
     * @return
     */
    ResponseData<List<EnvironmentOutputDto>> queryByProjectId(Integer projectId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ResponseData<EnvironmentOutputDto> getById(Integer id);

    /**
     * 创建
     * @param inputDto
     * @return
     */
    ResponseData<EnvironmentOutputDto> create(EnvironmentCreateInputDto inputDto);

    /**
     * 更新
     * @param inputDto
     * @return
     */
    ResponseData<EnvironmentOutputDto> update(EnvironmentUpdateInputDto inputDto);

    /**
     * 删除
     * @param id
     * @return
     */
    ResponseData<Boolean> delete(Integer id);
}
