package com.ex.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建redis模板对象");
        RedisTemplate redisTemplate = new RedisTemplate();
        //设置连接工厂对象，引入依赖时已经创建bean了，可以直接注入参数
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //设置redis key序列化器；
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
