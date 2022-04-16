package com.coconut.ds7.service;

import com.coconut.ds7.entity.BugEntity;

import java.util.List;

public interface BugService {
    /**
     * 查询
     *
     * @return
     */
    List<BugEntity> query();

    /**
     * 根据ID获取
     *
     * @param id
     * @return
     */
    BugEntity getById(Integer id);

    /**
     * 创建
     *
     * @param bugEntity
     * @return
     */
    BugEntity create(BugEntity bugEntity);

    /**
     * 修改
     *
     * @param bugEntity
     * @return
     */
    BugEntity update(BugEntity bugEntity);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 分页查询
     *
     * @param pageSize
     * @param pageIndex
     * @param name
     * @param reportUserId
     * @param gradeId
     * @param orderByStr
     * @return
     */
    List<BugEntity> queryForPage(int pageSize, int pageIndex, String name, Integer reportUserId, Integer gradeId, String orderByStr);

    /**
     * 根据id获取详情
     *
     * @param id
     * @return
     */
    BugEntity getDetailById(Integer id);
}
