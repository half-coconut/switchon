package com.coconut.ds20.advice;

import com.coconut.ds20.dto.common.ResponseData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/2/7 16:47
 * File: ExceptionHandler
 * Project: dS9
 */

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(BindException.class)
    public void handler(BindException ex, HttpServletResponse response) throws IOException {
        // 取到所有的规则性异常
        List<FieldError> fieldErrors = ex.getFieldErrors();
        String errorMessages = fieldErrors.stream().map(s->s.getField()+"["+s.getDefaultMessage()+"]").collect(Collectors.joining(","));

        // 截断输出
        ResponseData responseData = ResponseData.failure(errorMessages);
        responseData.setCode(8007);
        // spring里可以把对象转换成json字符串
        String result = new ObjectMapper().writeValueAsString(responseData);

        response.setStatus(8007);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(result);
    }

}
