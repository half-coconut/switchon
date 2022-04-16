package com.coconut.ds20.interceptor;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/17 1:07
 * File: AuthInterceptor
 * Project: dS20
 */

import com.coconut.ds20.annotation.UserRight;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.util.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 身份鉴权与授权验证
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是不是请求的action，如果不是就返回
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 通过反射的方式，获取注解
        UserRight userRight = handlerMethod.getMethodAnnotation(UserRight.class);

        if (userRight == null) {
            userRight = handlerMethod.getBean().getClass().getAnnotation(UserRight.class);
        }
        // 如果类和方法，都没有注解，就返回 true
        if (userRight == null) {
            return true;
        }
        // 如果有会话存在，就表示鉴权通过，进行授权验证；否则就，返回401，需要登录
        SessionUtil.CurrentUser currentUser = sessionUtil.getCurrentUser();
        if (currentUser != null) {
            // 获取当前的用户权限
            List<String> hasRoles = currentUser.getUserEntity().getRoles();
            List<String> needRoles = Arrays.asList(userRight.roles());
            // 如果我拥有的角色为空，或者 没有匹配上任何一条action需要的角色，就返回403
            if (hasRoles == null || hasRoles.stream().filter(s -> needRoles.contains(s)).count() <= 0) {
                // 截断输出
                ResponseData responseData = ResponseData.failure("拒绝访问，未被授权！");
                responseData.setCode(403);
                // spring里可以把对象转换成json字符串
                String result = new ObjectMapper().writeValueAsString(responseData);

                response.setStatus(403);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().println(result);
                return false;
            }
        } else {
            // 鉴权不通过，返回401
            ResponseData responseData = ResponseData.failure("未授权，请先登录进行身份验证");
            responseData.setCode(401);
            // spring里可以把对象转换成json字符串
            String result = new ObjectMapper().writeValueAsString(responseData);

            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(result);
            return false;
        }
        return true;
    }
}
