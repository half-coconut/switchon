package com.coconut.ds20.controller;

import com.coconut.ds20.DS20Application;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.environment.EnvironmentCreateInputDto;
import com.coconut.ds20.dto.input.environment.EnvironmentUpdateInputDto;
import com.coconut.ds20.dto.output.environment.EnvironmentOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 19:36
 * File: EnvironmentController
 * Project: dS20
 */
@Slf4j
@SpringBootTest(classes = DS20Application.class)
public class EnvironmentControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    EnvironmentController environmentController;

    Integer projectId = 3;
    Integer id = 1;
    String name = "单元测试";
    String host = "www.half-coconut.ut";
    Integer notExistProjectId = 100000;
    String dbConfig = "jdbc:mysql.half-coconut";

    @Test
    public void testEnvironmentQueryByProjectId() {
        ResponseData<List<EnvironmentOutputDto>> responseData;
        responseData = environmentController.queryByProjectId(projectId);
        log.info("请求返回结果为：{}", responseData);
        Assert.assertEquals(responseData.getData().size(),responseData.getTotal().intValue());
        Assert.assertEquals(responseData.getCode().intValue(),0);
        Assert.assertEquals(responseData.getMessage(),"操作成功。");
    }

    @Test
    public void testEnvironmentGetById() {
        ResponseData<EnvironmentOutputDto> responseData;
        responseData = environmentController.getById(id);
        log.info("请求返回结果为：{}", responseData);
        Assert.assertEquals(responseData.getCode().intValue(),0);
        Assert.assertEquals(responseData.getMessage(),"操作成功。");
    }

    @Test
    public void testEnvironmentCreate() {
        ResponseData<EnvironmentOutputDto> responseData;
        EnvironmentCreateInputDto inputDto = new EnvironmentCreateInputDto();
        inputDto.setName(name);
        inputDto.setHost(host);
        inputDto.setProjectId(notExistProjectId);
        inputDto.setDbConfig(dbConfig);
        responseData = environmentController.create(inputDto);
        log.info("请求返回结果为：{}", responseData);
        Assert.assertEquals(responseData.getCode().intValue(),1);
        Assert.assertEquals(responseData.getMessage(),"所属项目不存在");
    }

    @Test
    public void testEnvironmentUpdate() {
        ResponseData<EnvironmentOutputDto> responseData;
        EnvironmentUpdateInputDto inputDto = new EnvironmentUpdateInputDto();
        inputDto.setId(id);
        inputDto.setName(name);
        inputDto.setHost(host);
        inputDto.setProjectId(notExistProjectId);
        inputDto.setDbConfig(dbConfig);
        responseData = environmentController.update(inputDto);
        log.info("请求返回结果为：{}", responseData);
        Assert.assertEquals(responseData.getCode().intValue(),1);
        Assert.assertEquals(responseData.getMessage(),"所属项目不存在");
    }
}
