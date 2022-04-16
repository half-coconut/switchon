package com.coconut.ds7.repository.impl;

import com.coconut.ds7.entity.GradeEntity;
import com.coconut.ds7.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/27 22:29
 * File: GradeRepositoryImpl
 * Project: dS7
 */

@Repository
public class GradeRepositoryImpl implements GradeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<GradeEntity> queryAll() {
        String sql = "select id,name,description,create_by,update_by,create_time,update_time from grade";
        List<GradeEntity> result = jdbcTemplate.query(sql, new Object[]{}, new int[]{}, new BeanPropertyRowMapper<>(GradeEntity.class));
        return result;
    }

    @Override
    public GradeEntity getById(Integer id) {
        String sql = "select id,name,description,create_by,update_by,create_time,update_time from grade where id = ?";
        GradeEntity result = jdbcTemplate.queryForObject(sql, new Object[]{id}, new int[]{Types.INTEGER}, new BeanPropertyRowMapper<>(GradeEntity.class));
        return result;
    }

    @Override
    public GradeEntity creat(GradeEntity gradeEntity) {
        String sql = "insert into grade(name,description,create_by,update_by,create_time,update_time) values(?,?,?,?,?,?)";
//        jdbcTemplate.update(sql,gradeEntity.getName(),gradeEntity.getDescription(),gradeEntity.getCreateBy(),gradeEntity.getUpdateBy(),gradeEntity.getCreateTime(),gradeEntity.getUpdateTime());
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        Integer rows = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(sql, new String[]{"id"});// 主键页
                preparedStatement.setString(1, gradeEntity.getName());
                preparedStatement.setString(2, gradeEntity.getDescription());
                preparedStatement.setInt(3, gradeEntity.getCreateBy());
                preparedStatement.setInt(4, gradeEntity.getUpdateBy());
                preparedStatement.setTimestamp(5, new Timestamp(gradeEntity.getCreateTime().getTime()));
                preparedStatement.setTimestamp(6, new Timestamp(gradeEntity.getUpdateTime().getTime()));
                return preparedStatement;
            }
        }, generatedKeyHolder);
        Integer id = generatedKeyHolder.getKey().intValue();
        GradeEntity result = this.getById(id);
        return result;
    }

    @Override
    public GradeEntity update(GradeEntity gradeEntity) {
        String sql = "update grade set name=?,description=?,update_by=?,update_time=? where id = ?";
        jdbcTemplate.update(sql, gradeEntity.getName(), gradeEntity.getDescription(), gradeEntity.getUpdateBy(), gradeEntity.getUpdateTime(), gradeEntity.getId());
        GradeEntity result = this.getById(gradeEntity.getId());
        return result;
    }

    @Override
    public Integer delete(Integer id) {
        String sql = "delete from grade where id =?";
        return jdbcTemplate.update(sql, id);
    }
}
