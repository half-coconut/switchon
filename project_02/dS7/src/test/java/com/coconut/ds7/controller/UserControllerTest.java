package com.coconut.ds7.controller;

import com.coconut.ds7.DS7Application;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.output.user.UserOutputDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.List;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/15 18:33
 * File: UserControllerTest
 * Project: dS7
 */
@Slf4j
@SpringBootTest(classes = DS7Application.class)
public class UserControllerTest {
    @Autowired
    UserController userController;

    String username = "胖蛋";

    @Test
    public void testUserQueryAll() {
        ResponseData<List<UserOutputDto>> responseData;
        responseData = userController.queryAll();
        log.info("请求返回结果为：{}", responseData);
    }

    @Test
    public void testUserQueryByUsername() {
        ResponseData<List<UserOutputDto>> responseData;
        responseData = userController.query(1L, 5L, null, null, username);
        log.info("请求返回结果为：{}", responseData);
    }

    @Test
    public void testUserGetById() {
        ResponseData<UserOutputDto> responseData;
        responseData = userController.getById(14);
        log.info("请求返回结果为：{}", responseData);
    }

}
