package com.coconut.ds7.controller;

import com.coconut.ds7.entity.GradeEntity;
import com.coconut.ds7.service.GradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/28 0:09
 * File: GradeController
 * Project: dS7
 */

/**
 * 等级控制器
 */
@RestController
@RequestMapping("/grade")
@Api(value = "等级接口", tags = {"缺陷等级"})
public class GradeController {
    @Autowired          // 默认按类型 装配
    @Qualifier("xml")   // 表示按名称 装配
    GradeService gradeService;

    @ApiOperation(value = "查询所有", notes = "查询所有详情")
    @GetMapping("/")
    public List<GradeEntity> queryAll() {
        return gradeService.queryAll();
    }

    @ApiOperation(value = "根据ID查询", notes = "根据ID查询详情")
    @GetMapping("/{id}")
    public GradeEntity getById(@PathVariable Integer id) {
        return gradeService.getById(id);
    }

    @ApiOperation(value = "创建", notes = "创建一条数据")
    @PostMapping("/")
    public GradeEntity create(@RequestBody GradeEntity gradeEntity) {
        return gradeService.create(gradeEntity);
    }


    @ApiOperation(value = "修改", notes = "修改一条数据")
    @PutMapping("/")
    public GradeEntity update(@RequestBody GradeEntity gradeEntity) {
        return gradeService.update(gradeEntity);
    }


    @ApiOperation(value = "删除", notes = "删除一条数据")
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return gradeService.delete(id);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询详情")
    @GetMapping("/queryForPage")
    public List<GradeEntity> queryForPage(Integer pageSize, Integer pageIndex, String name, String orderByStr) {
        return gradeService.queryForPage(pageSize, pageIndex, name, orderByStr);
    }

}
