package com.coconut.ds7.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds7.dto.common.RequestData;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.common.page.Paging;
import com.coconut.ds7.dto.input.version.VersionCreateInputDto;
import com.coconut.ds7.dto.input.version.VersionQueryInputDto;
import com.coconut.ds7.dto.input.version.VersionUpdateInputDto;
import com.coconut.ds7.dto.output.version.VersionOutputDto;
import com.coconut.ds7.entity.VersionEntity;
import com.coconut.ds7.mapper.VersionMapper;
import com.coconut.ds7.service.VersionService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/8 12:59
 * File: VersionServiceImpl
 * Project: dS7
 */

@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, VersionEntity> implements VersionService {
    /**
     * 分页查询，兼容不分页
     *
     * @param requestData
     * @return
     */
    @Override
    public ResponseData<List<VersionOutputDto>> query(RequestData<VersionQueryInputDto> requestData) {
        ResponseData<List<VersionOutputDto>> responseData = new ResponseData<>();
        try {
            QueryWrapper<VersionEntity> queryWrapper = new QueryWrapper<>();
            // 查询条件
            VersionQueryInputDto condition = requestData.getConditions();
            if (condition.getName() != null) {
                queryWrapper.like("name", condition.getName());
            }
            // 分页
            if (requestData.getPage() == null) {
                requestData.setPage(new Paging(1L, Long.MAX_VALUE));
            }
            // 获取数据
            IPage<VersionEntity> queryPage = new Page<>(requestData.getPage().getPgaeIndex(), requestData.getPage().getPgaeSize());
            queryPage = this.page(queryPage, queryWrapper);
            List<VersionOutputDto> outputDtos = new ArrayList<>();
            queryPage.getRecords().forEach(item -> {
                VersionOutputDto outputDto = new VersionOutputDto();
                BeanUtils.copyProperties(item, outputDto);
                outputDtos.add(outputDto);
            });
            responseData.setData(outputDtos);
            responseData.setTotal(queryPage.getTotal());
            responseData.setCode(ResponseData.SUCCESS_CODE);
            responseData.setMessage("操作成功！");

        } catch (Exception e) {
            responseData = ResponseData.failure(e.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<VersionOutputDto> getById(Integer id) {
        ResponseData<VersionOutputDto> responseData;
        try {
            VersionEntity versionEntity = super.getById(id);
            VersionOutputDto outputDto = new VersionOutputDto();
            BeanUtils.copyProperties(versionEntity, outputDto);
            responseData = ResponseData.success(outputDto);

        } catch (Exception ex) {
            responseData = new ResponseData();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<VersionOutputDto> create(VersionCreateInputDto inputDto) {
        ResponseData<VersionOutputDto> responseData;
        try {
            // 对象转换
            VersionEntity entity = new VersionEntity();
            entity.setCreateBy(1);
            entity.setUpdateBy(1);
            BeanUtils.copyProperties(inputDto, entity);
            // 新增
            Boolean success = this.save(entity);
            // 获取最新数据
            responseData = this.getById(entity.getId());
        } catch (Exception ex) {
            responseData = new ResponseData();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<VersionOutputDto> update(VersionUpdateInputDto inputDto) {
        ResponseData<VersionOutputDto> responseData;
        try {
            // 对象转换
            VersionEntity entity = new VersionEntity();
            BeanUtils.copyProperties(inputDto, entity);
            // 修改
            Boolean success = this.updateById(entity);
            // 获取最新数据
            responseData = this.getById(entity.getId());
        } catch (Exception ex) {
            responseData = new ResponseData();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> delete(Integer id) {
        ResponseData<Boolean> responseData;
        try {
            // 删除
            Boolean success = this.removeById(id);
            // 获取最新数据
            responseData = ResponseData.success(success);
        } catch (Exception ex) {
            responseData = new ResponseData();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<VersionOutputDto>> queryAll() {
        List<VersionEntity> versionEntities = this.list();
        List<VersionOutputDto> outputDtos = new ArrayList<>();
        versionEntities.forEach(item -> {
            VersionOutputDto outputDto = new VersionOutputDto();
            BeanUtils.copyProperties(item, outputDto);
            outputDtos.add(outputDto);
        });
        return ResponseData.success(outputDtos);
    }
}
