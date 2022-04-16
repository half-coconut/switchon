package com.coconut.ds7.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedistTemplateUtil {
    @Autowired
    RedisCacheManager redisCacheManager;

    @Value("${spring.cache.redis.time-to-live}")
    Duration ttl;

    public Duration getExpire(String cacheKey) {
        Duration result = ttl;
        //获取当前键的配置
        RedisCacheConfiguration redisCacheConfiguration = redisCacheManager.getCacheConfigurations().get(cacheKey);
        //如果为空，获取全局过期配置
        if (redisCacheConfiguration != null) {
            result = redisCacheConfiguration.getTtl();
        }

        return result;
    }
}

