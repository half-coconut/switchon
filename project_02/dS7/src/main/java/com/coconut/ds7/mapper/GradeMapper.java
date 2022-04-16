package com.coconut.ds7.mapper;

import com.coconut.ds7.entity.GradeEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2021/12/30 22:24
 * File: GradeMapper
 * Project: dS7
 */

/**
 * 版本的mapper，使用注解形式配置与数据库表操作的关系
 */
@Mapper
public interface GradeMapper {
    // 查询所有
//    @Select("select id,name,description,create_by,update_by,create_time,update_time from grade")
    List<GradeEntity> queryAll();

    // 根据id获取
//    @Select("select id,name,description,create_by,update_by,create_time,update_time from grade where id =#{id}")
    GradeEntity getById(Integer id);

    // 新增
//    @Insert("insert into grade(name,description,create_by,update_by,create_time,update_time) values(#{name},#{description},#{createBy},#{updateBy},#{createTime},#{updateTime})")
    Integer create(GradeEntity gradeEntity);

    // 修改
//    @Update("update grade set name = #{name},description = #{description},create_by=#{createBy},update_by=#{updateBy},create_time=#{createTime},update_time=#{updateTime} where id = #{id}")
    Integer update(GradeEntity gradeEntity);

    // 删除
//    @Delete("delete from grade where id = #{id}")
    Integer delete(Integer id);

    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageIndex
     * @param name
     * @return
     */
    List<GradeEntity> queryForPage(Integer pageSize, Integer pageIndex, String name, String orderByStr);
}
