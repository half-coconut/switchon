package com.coconut.ds7.mapper;

import com.coconut.ds7.entity.BugVersionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BugVersionMapper {
    List<BugVersionEntity> query();

    BugVersionEntity getById(Integer id);

    Integer create(BugVersionEntity bugVersionEntity);

    Integer update(BugVersionEntity bugVersionEntity);

    Integer delete(Integer id);

    List<BugVersionEntity> getByBugId(Integer bugId);
}
