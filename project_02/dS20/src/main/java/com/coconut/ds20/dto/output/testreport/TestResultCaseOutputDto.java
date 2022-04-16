package com.coconut.ds20.dto.output.testreport;

import com.coconut.ds20.dto.output.inf.InterfaceOutputDto;
import com.coconut.ds20.dto.output.testcase.TestCaseOutputDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 0:38
 * File: TestResultCaseOutputDto
 * Project: dS20
 */

@Data
public class TestResultCaseOutputDto {
    /**
     * 测试用例
     */
    private TestCaseOutputDto testCase;
    /**
     * 对应接口
     */
    private InterfaceOutputDto inf;
    /**
     * 接口调用开始时间
     */
    private Date startTime;
    /**
     * 接口调用结束时间
     */
    private Date endTime;
    /**
     * 状态，0表示成功，1表示异常，2表示错误
     */
    private Integer status;
    /**
     * 返回内容
     */
    private String responseData;
    /**
     * 异常信息，如果调用异常的话
     */
    private String exception;

    /**
     * 响应提取
     */
    private List<TestResultCaseCommonDto> extracts;

    /**
     * 断言
     */
    private List<TestResultCaseCommonDto> asserts;
    /**
     * 数据库断言
     */
    private List<TestResultCaseCommonDto> dbAsserts;
}
