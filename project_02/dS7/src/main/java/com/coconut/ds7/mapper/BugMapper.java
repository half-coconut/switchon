package com.coconut.ds7.mapper;

import com.coconut.ds7.entity.BugEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BugMapper {
    List<BugEntity> query();

    BugEntity getById(Integer id);

    Integer create(BugEntity bugEntity);

    Integer update(BugEntity bugEntity);

    Integer delete(Integer id);

    List<BugEntity> queryForPage(int pageSize, int pageIndex, String title, Integer reportUserId, Integer gradeId, String orderByStr);

    BugEntity getDetailById(Integer id);
}
