package com.coconut.ds7.controller;

import com.coconut.ds7.entity.BugEntity;
import com.coconut.ds7.service.BugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/5 1:11
 * File: BugController
 * Project: dS7
 */
@Api(value = "缺陷接口", tags = {"缺陷及详情"})
@RestController
@RequestMapping("/bug")
@CrossOrigin(allowCredentials = "true", originPatterns = "*")
public class BugController {
    @Autowired
    BugService bugService;

    @ApiOperation(value = "查询", notes = "查询数据")
    @GetMapping("/")
    public List<BugEntity> query() {
        return bugService.query();
    }

    @ApiOperation(value = "根据ID获取数据", notes = "根据ID获取数据详情")
    @GetMapping("/{id}")
    public BugEntity getById(@PathVariable Integer id) {
        return bugService.getById(id);
    }

    @ApiOperation(value = "创建", notes = "创建一条新的数据")
    @PostMapping("/")
    public BugEntity create(@RequestBody BugEntity bugEntity) {
        return bugService.create(bugEntity);
    }

    @ApiOperation(value = "更新", notes = "更新一条新的数据")
    @PutMapping("/")
    public BugEntity update(@RequestBody BugEntity bugEntity) {
        return bugService.update(bugEntity);
    }

    @ApiOperation(value = "删除", notes = "删除一条新的数据")
    @DeleteMapping("/{id}")
    public Integer delete(@PathVariable Integer id) {
        return bugService.delete(id);
    }

    @ApiOperation(value = "分页查询", notes = "分页查询数据")
    @GetMapping("/queryForPage")
    public List<BugEntity> queryForPage(int pageSize, int pageIndex, String name, Integer reportUserId, Integer gradeId, String orderByStr) {
        return bugService.queryForPage(pageSize, pageIndex, name, reportUserId, gradeId, orderByStr);
    }

    @ApiOperation(value = "根据ID获取详情数据", notes = "根据ID获取详情数据")
    @GetMapping("/getDetailById")
    public BugEntity getDetailById(@RequestParam Integer id) {
        return bugService.getDetailById(id);
    }

}
