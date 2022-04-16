package com.coconut.ds20.service.impl;

import com.coconut.ds20.dto.output.testreport.TestResultCaseOutputDto;
import com.coconut.ds20.entity.EnvironmentEntity;
import com.coconut.ds20.entity.InterfaceEntity;
import com.coconut.ds20.entity.TestCaseEntity;
import com.coconut.ds20.entity.TestRecordEntity;
import com.coconut.ds20.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/15 16:49
 * File: TaskTestService
 * Project: dS20
 */

/**
 * 测试任务执行实现类
 */
@Service
public class TaskTestService {

    @Autowired
    SessionUtil sessionUtil;

    @Transactional
    public void test(EnvironmentEntity environmentEntity, List<InterfaceEntity> interfaceEntities, List<TestCaseEntity> testCaseEntities, TestRecordEntity testRecordEntity, SessionUtil.CurrentUser currentUser){
        // TODO
    }

//    private TestResultCaseOutputDto testCase(EnvironmentEntity environmentEntity, List<InterfaceEntity> interfaceEntities, List<TestCaseEntity> testCaseEntities, TestRecordEntity testRecordEntity){
//        TestResultCaseOutputDto testResultCaseOutputDto = new TestResultCaseOutputDto();
//        // TODO
//        return TestResultCaseOutputDto;
//    }


}
