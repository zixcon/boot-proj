package com.holdon.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


/**
 * https://docs.spring.io/spring-data/redis
 * Created by wd on 2018/1/27.
 */
@Configuration
public class RedisConfig {

    /**
     *
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        // jedis
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration() .master("mymaster")
//                .sentinel("127.0.0.1", 26379) .sentinel("127.0.0.1", 26380);
//        return new JedisConnectionFactory(sentinelConfig);
        // lettuce
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master("mymaster")
//                .sentinel("127.0.0.1", 26379) .sentinel("127.0.0.1", 26380);
//        return new LettuceConnectionFactory(sentinelConfig);
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        // explicitly enable transaction support
        // template.setEnableTransactionSupport(true);
        template.setValueSerializer(jackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

    /**
     * Support for Spring Cache Abstraction
     * declare Redis Cache Manager
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setDefaultExpiration(60);
        return redisCacheManager;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder builder = new StringBuilder();
            builder.append(o.getClass().getName());
            builder.append(".");
            builder.append(method.getName());
            builder.append("():");
            for (Object obj : objects) {
                builder.append(obj.toString());
                builder.append("|");
            }
            return null;
        };
//        return new KeyGenerator() {
//            @Override
//            public Object generate(Object o, Method method, Object... objects) {
//                StringBuilder builder = new StringBuilder();
//                builder.append(o.getClass().getName());
//                builder.append(".");
//                builder.append(method.getName());
//                builder.append("():");
//                for (Object obj : objects) {
//                    builder.append(obj.toString());
//                }
//                return null;
//            }
//        };
    }

    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
