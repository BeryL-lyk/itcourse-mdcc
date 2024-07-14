package com.itcourse.mdcc.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

//缓存的配置
@Configuration
public class RedisConfig {

    @Resource
    private RedisConnectionFactory factory;


    //使用JSON进行序列化
    @Bean
    public RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(factory);
        //JSON格式序列化
        GenericFastJsonRedisSerializer serializer = new GenericFastJsonRedisSerializer();
        //key的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value的序列化
        redisTemplate.setValueSerializer(serializer);
        //hash结构key的序列化
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //hash结构value的序列化
        redisTemplate.setHashValueSerializer(serializer);
        return redisTemplate;
    }

}
