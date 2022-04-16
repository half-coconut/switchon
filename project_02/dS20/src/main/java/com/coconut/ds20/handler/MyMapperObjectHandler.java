package com.coconut.ds20.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.coconut.ds20.util.SessionUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/2/14 1:36
 * File: MyMapperObjectHandler
 * Project: dS9
 */

@Component
public class MyMapperObjectHandler implements MetaObjectHandler {
    @Autowired
    SessionUtil sessionUtil;

    @Override
    public void insertFill(MetaObject metaObject) {
        SessionUtil.CurrentUser currentUser = sessionUtil.getCurrentUser();
        Date currentDate = new Date();
        this.setFieldValByName("createById", currentUser.getUserEntity().getId(), metaObject);
        this.setFieldValByName("createByName", currentUser.getUserEntity().getUsername(), metaObject);
        this.setFieldValByName("createTime", currentDate, metaObject);
        this.setFieldValByName("updateById", currentUser.getUserEntity().getId(), metaObject);
        this.setFieldValByName("updateByName", currentUser.getUserEntity().getUsername(), metaObject);
        this.setFieldValByName("updateTime", currentDate, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SessionUtil.CurrentUser currentUser = sessionUtil.getCurrentUser();
        Date currentDate = new Date();
        this.setFieldValByName("updateById", currentUser.getUserEntity().getId(), metaObject);
        this.setFieldValByName("updateByName", currentUser.getUserEntity().getUsername(), metaObject);
        this.setFieldValByName("updateTime", currentDate, metaObject);
    }
}
