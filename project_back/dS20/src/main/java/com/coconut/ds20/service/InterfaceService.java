package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.inf.InterfaceCreateInputDto;
import com.coconut.ds20.dto.input.inf.InterfaceUpdateInputDto;
import com.coconut.ds20.dto.output.inf.InterfaceOutputDto;
import com.coconut.ds20.dto.output.inf.InterfaceQueryOutputDto;
import com.coconut.ds20.entity.InterfaceEntity;

import java.util.List;

/**
 * 接口服务
 */
public interface InterfaceService extends IService<InterfaceEntity> {
    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageIndex
     * @param moduleId
     * @param projectId
     * @return
     */
    ResponseData<List<InterfaceQueryOutputDto>> query(Integer pageSize, Integer pageIndex, Integer moduleId, Integer projectId);

    /**
     * 查询所有
     *
     * @param projectId
     * @return
     */
    ResponseData<List<InterfaceOutputDto>> queryByProjectId(Integer projectId);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    ResponseData<InterfaceOutputDto> getById(Integer id);

    /**
     * 创建
     *
     * @param inputDto
     * @return
     */
    ResponseData<InterfaceOutputDto> create(InterfaceCreateInputDto inputDto);

    /**
     * 更新
     *
     * @param inputDto
     * @return
     */
    ResponseData<InterfaceOutputDto> update(InterfaceUpdateInputDto inputDto);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    ResponseData<Boolean> delete(Integer id);

}
