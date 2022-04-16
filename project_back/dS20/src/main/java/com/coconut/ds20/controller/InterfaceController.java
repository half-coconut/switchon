package com.coconut.ds20.controller;

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.inf.InterfaceCreateInputDto;
import com.coconut.ds20.dto.input.inf.InterfaceUpdateInputDto;
import com.coconut.ds20.dto.output.inf.InterfaceOutputDto;
import com.coconut.ds20.dto.output.inf.InterfaceQueryOutputDto;
import com.coconut.ds20.service.InterfaceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/6 18:44
 * File: InterfaceController
 * Project: dS20
 */

@Api(value = "接口信息",tags = {"接口信息的详情"})
@RestController
@RequestMapping("interface")
@UserRight(roles = {"admin","staff"})
public class InterfaceController {
    @Autowired
    InterfaceService interfaceService;

    @GetMapping()
    ResponseData<List<InterfaceQueryOutputDto>> query(@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "1") Integer pageIndex, Integer moduleId, @RequestParam Integer projectId){
        return interfaceService.query(pageSize,pageIndex,moduleId,projectId);
    }

    @GetMapping("queryByProjectId")
    ResponseData<List<InterfaceOutputDto>> queryByProjectId(@RequestParam Integer projectId){
        return interfaceService.queryByProjectId(projectId);
    }

    @GetMapping("getById/{id}")
    ResponseData<InterfaceOutputDto> getById(@PathVariable Integer id){
        return interfaceService.getById(id);
    }

    @PostMapping("create")
    ResponseData<InterfaceOutputDto> create(@RequestBody @Validated InterfaceCreateInputDto inputDto){
        return interfaceService.create(inputDto);
    }

    @PostMapping("update")
    ResponseData<InterfaceOutputDto> update(@RequestBody @Validated InterfaceUpdateInputDto inputDto){
        return interfaceService.update(inputDto);
    }

    @GetMapping("delete/{id}")
    ResponseData<Boolean> delete(@PathVariable Integer id){
        return interfaceService.delete(id);
    }
}
