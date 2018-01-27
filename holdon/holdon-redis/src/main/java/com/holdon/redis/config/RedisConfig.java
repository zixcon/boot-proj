package com.holdon.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

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
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }
}
