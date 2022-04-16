package com.coconut.ds7.service.impl;

import com.coconut.ds7.entity.GradeEntity;
import com.coconut.ds7.repository.GradeRepository;
import com.coconut.ds7.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/28 0:07
 * File: GradeServiceImpl
 * Project: dS7
 */

@Service(value = "jdbc")
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public List<GradeEntity> queryAll() {
        // TODO 实际项目更复杂
        return gradeRepository.queryAll();
    }

    @Override
    public GradeEntity getById(Integer id) {
        return gradeRepository.getById(id);
    }

    @Override
    public GradeEntity create(GradeEntity gradeEntity) {
        return gradeRepository.creat(gradeEntity);
    }

    @Override
    public GradeEntity update(GradeEntity gradeEntity) {
        return gradeRepository.update(gradeEntity);
    }

    @Override
    public Integer delete(Integer id) {
        return gradeRepository.delete(id);
    }

    @Override
    public List<GradeEntity> queryForPage(Integer pageSize, Integer pageIndex, String name, String orderByStr) {
        // TODO 未实现
        return null;
    }
}
