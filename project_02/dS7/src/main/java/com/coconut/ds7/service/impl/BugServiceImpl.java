package com.coconut.ds7.service.impl;

import com.coconut.ds7.entity.BugEntity;
import com.coconut.ds7.entity.BugVersionEntity;
import com.coconut.ds7.mapper.BugMapper;
import com.coconut.ds7.mapper.BugVersionMapper;
import com.coconut.ds7.service.BugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/5 1:07
 * File: BugServiceImpl
 * Project: dS7
 */
@Slf4j
@Service
public class BugServiceImpl implements BugService {
    @Autowired
    BugMapper bugMapper;

    @Autowired
    BugVersionMapper bugVersionMapper;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Override
    @Transactional
    public List<BugEntity> query() {
        // 【一级缓存】添加Transactional后，会开启一级缓存，同一个Mapper的数据不会多次查询数据库，默认开启
        bugMapper.query();
        bugMapper.query();
        return bugMapper.query();
    }

    @Override
    public BugEntity getById(Integer id) {
        return bugMapper.getById(id);
    }

    /**
     * 【声明式事务】
     * 创建：1.添加bug信息 2.关联bug和版本信息
     *
     * @param bugEntity
     * @return
     */
    @Override
    @Transactional
    public BugEntity create(BugEntity bugEntity) {
        // 新增bug
        Integer result = bugMapper.create(bugEntity);

        // 新增bug关联版本
        List<BugVersionEntity> bugVersionEntities = bugEntity.getBugVersion();
        if (bugVersionEntities != null && bugVersionEntities.size() > 0) {
            bugVersionEntities.forEach(item -> {
                item.setBugId(bugEntity.getId());
                bugVersionMapper.create(item);
            });
        }
        return bugEntity;
    }

    /**
     * 【编程式事务】 使用 TransactionTemplate
     * 更新：1.更新bug信息，2.删除旧的关联信息，3.添加新的关联信息
     *
     * @param bugEntity
     * @return
     */
    @Override
    public BugEntity update(BugEntity bugEntity) {
        Integer result = transactionTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                // 1.更新bug信息
                Integer result = bugMapper.update(bugEntity);
                // 2.删除旧的关联版本信息
                List<BugVersionEntity> bugVersionEntitiesOfExist = bugVersionMapper.getByBugId(bugEntity.getId());
                if (bugVersionEntitiesOfExist != null && bugVersionEntitiesOfExist.size() > 0) {
                    bugVersionEntitiesOfExist.forEach(item -> {
                        bugVersionMapper.delete(item.getId());
                    });
                }

                // 3.添加新的版本信息
                List<BugVersionEntity> bugVersionEntitiesOfNew = bugEntity.getBugVersion();
                if (bugVersionEntitiesOfNew != null && bugVersionEntitiesOfNew.size() > 0) {
                    bugVersionEntitiesOfNew.forEach(item -> {
                        item.setBugId(bugEntity.getId());
                        bugVersionMapper.create(item);
                    });
                }
                return result;
            }
        });
        return bugEntity;
    }

    /**
     * 【编程式事务】
     * 删除：1.删除bug信息 2.删除关联版本信息
     *
     * @param id
     * @return
     */
    @Override
    public Integer delete(Integer id) {
        return bugMapper.delete(id);
    }

    @Override
    public List<BugEntity> queryForPage(int pageSize, int pageIndex, String title, Integer reportUserId, Integer gradeId, String orderByStr) {
        return bugMapper.queryForPage(pageSize, pageIndex, title, reportUserId, gradeId, orderByStr);
    }

    @Override
    public BugEntity getDetailById(Integer id) {
        log.info("这是一个方法");
        BugEntity bugEntity = bugMapper.getDetailById(id);
        return bugEntity;
    }
}
