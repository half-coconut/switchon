package com.coconut.ds20.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Redis相关配置
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
    @Value("${spring.cache.redis.time-to-live}")
    Long ttl;
    @Autowired
    CacheKeyProperties cacheKeyProperties;

    /**
     * 缓存管理器
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        //初始化RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());

        //修改Redis缓存的配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()))    //设置序列化模式为redisTemplate的序列化方式
                //.disableCachingNullValues()         //不缓存空值
                .entryTtl(Duration.ofMillis(ttl));      //使用配置后，application.properties中的配置失效，手动添加

        //添加需要初始化的缓存名称及对应的过期时间
        Set<String> cacheNames = new HashSet<>();    //缓存名称Set
        ConcurrentHashMap<String, RedisCacheConfiguration> cacheConfigs = new ConcurrentHashMap<>();     //缓存名称对应动态缓存时间Map
        //遍历application中的配置
        for (Map.Entry<String, Duration> entry : cacheKeyProperties.getCacheConfigs().entrySet()) {
            //添加初始的缓存名称
            cacheNames.add(entry.getKey());
            //添加初始的缓存名称对应的过期时间
            cacheConfigs.put(entry.getKey(), redisCacheConfiguration.entryTtl(entry.getValue()));
        }

        //获取出新CacheManager
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisCacheWriter).cacheDefaults(redisCacheConfiguration).initialCacheNames(cacheNames).withInitialCacheConfigurations(cacheConfigs).build();
        return redisCacheManager;
    }

    /**
     * 重新注入修改过系列化方式的RedisTemplate
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //设置连接工厂
        redisTemplate.setConnectionFactory(factory);

        //定义使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的二进制序列化方式）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //设置序列化的Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        //设置要系列化的的属性和作用域范围
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 设置序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);   //过时方法
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);


        //设置key使用StringRedisSerializer进行序列化和反序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //设置value使用jackson2JsonRedisSerializer进行序列化反序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        //设置hash的key使用StringRedisSerializer进行序列化和反序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //设置hash的key使用jackson2JsonRedisSerializer进行序列化和反序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        //应用所有配置
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}