package com.coconut.ds7.controller;

import com.coconut.ds7.dto.common.RequestData;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.common.page.Paging;
import com.coconut.ds7.dto.input.version.VersionCreateInputDto;
import com.coconut.ds7.dto.input.version.VersionQueryInputDto;
import com.coconut.ds7.dto.input.version.VersionUpdateInputDto;
import com.coconut.ds7.dto.output.version.VersionOutputDto;
import com.coconut.ds7.service.VersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/8 16:15
 * File: VersionController
 * Project: dS7
 */
@Api(value = "版本接口", tags = {"版本接口"})
@RestController
@RequestMapping("/version")
public class VersionController {
    @Autowired
    VersionService versionService;

    @ApiOperation(value = "查询", notes = "查询数据")
    @GetMapping("/")
    public ResponseData<List<VersionOutputDto>> query(@RequestParam(defaultValue = "1") Long pageIndex, @RequestParam(defaultValue = "5") Long pageSize, String name, String orderBy) {
        RequestData<VersionQueryInputDto> requestData = new RequestData<>();
        Paging paging = new Paging();
        paging.setPgaeIndex(pageIndex);
        paging.setPgaeSize(pageSize);
        requestData.setPage(paging);
        requestData.setOrderBy(orderBy);
        VersionQueryInputDto queryInputDto = new VersionQueryInputDto();
        queryInputDto.setName(name);
        requestData.setConditions(queryInputDto);

        return versionService.query(requestData);
    }

    @ApiOperation(value = "查询所有", notes = "查询所有数据")
    @GetMapping("/queryAll")
    public ResponseData<List<VersionOutputDto>> queryAll() {
        return versionService.queryAll();
    }

    @ApiOperation(value = "根据ID获取数据", notes = "根据ID获取数据详情")
    @GetMapping("/{id}")
    public ResponseData<VersionOutputDto> getById(@PathVariable Integer id) {
        return versionService.getById(id);
    }

    @ApiOperation(value = "创建", notes = "创建一条新数据")
    @PostMapping("/")
    public ResponseData<VersionOutputDto> create(@RequestBody VersionCreateInputDto inputDto) {
        return versionService.create(inputDto);
    }

    @ApiOperation(value = "修改", notes = "修改一条已存在数据")
    @PutMapping("/")
    public ResponseData<VersionOutputDto> update(@RequestBody VersionUpdateInputDto inputDto) {
        return versionService.update(inputDto);
    }

    @ApiOperation(value = "删除", notes = "删除一条已存在数据")
    @DeleteMapping("/{id}")
    public ResponseData<Boolean> delete(@PathVariable Integer id) {
        return versionService.delete(id);
    }
}