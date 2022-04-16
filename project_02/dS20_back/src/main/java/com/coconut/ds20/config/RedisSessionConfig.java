package com.coconut.ds20.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/2/16 22:45
 * File: RedisSessionConfig
 * Project: dS9
 */

/**
 * 将会话托管到redis，在分布式部署时，实现多负载共享会话信息
 * 加注解 @EnableRedisHttpSession，在启动类上加也可以
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
