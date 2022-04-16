package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.module.ModuleCreateInputDto;
import com.coconut.ds20.dto.input.module.ModuleUpdateInputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import com.coconut.ds20.entity.ModuleEntity;

import java.util.List;

/**
 * 模块服务
 */
public interface ModuleService extends IService<ModuleEntity> {
    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageIndex
     * @param projectId
     * @return
     */
    ResponseData<List<ModuleOutputDto>> query(Integer pageSize, Integer pageIndex, Integer projectId);

    /**
     * 根据项目id，查询全部
     *
     * @param projectId
     * @return
     */
    ResponseData<List<ModuleOutputDto>> queryByProjectId(Integer projectId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ResponseData<ModuleOutputDto> getById(Integer id);

    /**
     * 创建
     *
     * @param inputDto
     * @return
     */
    ResponseData<ModuleOutputDto> create(ModuleCreateInputDto inputDto);

    /**
     * 更新
     *
     * @param inputDto
     * @return
     */
    ResponseData<ModuleOutputDto> update(ModuleUpdateInputDto inputDto);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    ResponseData<Boolean> delete(Integer id);

}
