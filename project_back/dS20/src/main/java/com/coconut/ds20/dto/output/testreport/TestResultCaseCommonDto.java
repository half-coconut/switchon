package com.coconut.ds20.dto.output.testreport;

import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 0:39
 * File: TestResultCaseCommonDto
 * Project: dS20
 */

@Data
public class TestResultCaseCommonDto {
    /**
     * 表达式，分别为响应提取、用例断言、数据库断言时的表达式
     */
    private String assertExpression;
    /**
     * 响应提取、断言结果
     */
    private  Boolean result;
    /**
     * 真实值，如果断言结果为false，保存真实值
     */
    private String realValue;
}
