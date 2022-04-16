package com.coconut.ds20.util;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/17 0:40
 * File: SessionUtil
 * Project: dS20
 */

import com.coconut.ds20.entity.UserEntity;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * 会话工具
 */
@Component
public class SessionUtil {
    @Autowired
    HttpSession httpSession;

    private static final String CURRENT_USER_KEY = "current_user";

    // 设置当前登录用户
    public void setCurrentUser(CurrentUser currentUser) {
        httpSession.setAttribute(CURRENT_USER_KEY, currentUser);
    }

    // 获取当前登录用户
    public CurrentUser getCurrentUser() {
        // 初始化不能new一个对象，否则就会跳过为空判断
        CurrentUser currentUser = null;
        Object result = httpSession.getAttribute(CURRENT_USER_KEY);
        if (result != null) {
            currentUser = (CurrentUser) result;
        }
        return currentUser;
    }

    @Data
    public static class CurrentUser implements Serializable {
        private UserEntity userEntity;
    }
}
