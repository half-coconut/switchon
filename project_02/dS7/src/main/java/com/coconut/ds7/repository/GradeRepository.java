package com.coconut.ds7.repository;

import com.coconut.ds7.entity.GradeEntity;

import java.util.List;

/**
 * 等级访问接口
 */
public interface GradeRepository {
    // 查询所有
    List<GradeEntity> queryAll();

    // 根据id获取
    GradeEntity getById(Integer id);

    // 新增
    GradeEntity creat(GradeEntity gradeEntity);

    // 修改
    GradeEntity update(GradeEntity gradeEntity);

    // 删除
    Integer delete(Integer id);
}
