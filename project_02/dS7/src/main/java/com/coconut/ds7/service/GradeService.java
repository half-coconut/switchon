package com.coconut.ds7.service;

import com.coconut.ds7.entity.GradeEntity;

import java.util.List;

public interface GradeService {
    // 查询所有
    List<GradeEntity> queryAll();

    // 根据id获取
    GradeEntity getById(Integer id);

    // 新增
    GradeEntity create(GradeEntity gradeEntity);

    // 修改
    GradeEntity update(GradeEntity gradeEntity);

    // 删除
    Integer delete(Integer id);

    // 分页查询
    List<GradeEntity> queryForPage(Integer pageSize, Integer pageIndex, String name, String orderByStr);
}
