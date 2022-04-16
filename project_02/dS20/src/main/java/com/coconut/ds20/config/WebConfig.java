package com.coconut.ds20.config;

import com.coconut.ds20.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/17 21:34
 * File: WebConfig
 * Project: dS20
 */

/**
 * 全局的web拦截
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    AuthInterceptor authInterceptor;

    /**
     * 添加拦截器和过滤规则，并排除部分swagger-ui的静态资源
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/account/login")
                .excludePathPatterns("/swagger-resources/**", "/webjars", "/v2/**", "/swagger-ui.html/");
    }

    /**
     * 配置允许跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)     // 允许跨域发送cookie
                .allowedOriginPatterns("*")     // 允许所有域名进行跨域调用
                .allowedHeaders("*")    // 放行全部原始头信息
                .allowedMethods("*");   // 允许所有请求方法跨域调用
    }
}
