package com.coconut.ds7.service.impl;

import com.coconut.ds7.entity.GradeEntity;
import com.coconut.ds7.mapper.GradeMapper;
import com.coconut.ds7.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/30 22:40
 * File: GradeServiceImplOfXML
 * Project: dS7
 */

@Service("xml")
//@Primary
public class GradeServiceImplOfXML implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

    @Override
    public List<GradeEntity> queryAll() {
        return gradeMapper.queryAll();
    }

    @Override
    public GradeEntity getById(Integer id) {
        return gradeMapper.getById(id);
    }

    @Override
    public GradeEntity create(GradeEntity gradeEntity) {
        gradeMapper.create(gradeEntity);
        return gradeEntity;
    }

    @Override
    public GradeEntity update(GradeEntity gradeEntity) {
        gradeMapper.update(gradeEntity);
        return gradeEntity;
    }

    @Override
    public Integer delete(Integer id) {
        return gradeMapper.delete(id);
    }

    @Override
    public List<GradeEntity> queryForPage(Integer pageSize, Integer pageIndex, String name, String orderByStr) {
        return gradeMapper.queryForPage(pageSize, pageIndex, name, orderByStr);
    }
}
