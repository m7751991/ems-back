package com.example.educationalsystembackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis对json序列化处理
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        // key   采用StringRedisSerializer
        // value 采用GenericJackson2JsonRedisSerializer

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 关闭启用默认配置
        template.setEnableDefaultSerializer(false);
        // 连接工厂
        template.setConnectionFactory(factory);
        // key 序列化方式
        template.setKeySerializer(RedisSerializer.string());
        // value 序列化方式
        template.setValueSerializer(RedisSerializer.json());
        // hash key 序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        // hash value 序列化方式
        template.setHashValueSerializer(RedisSerializer.json());
        // 配置完成
        template.afterPropertiesSet();
        return template;
    }
}
