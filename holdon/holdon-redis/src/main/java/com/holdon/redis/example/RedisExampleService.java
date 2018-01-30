package com.holdon.redis.example;

import com.holdon.redis.config.RedisCacheNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Cacheable 触发缓存入口
 * @CacheEvict 触发移除缓存
 * @CacahePut 更新缓存
 * @Caching 将多种缓存操作分组
 * @CacheConfig 类级别的缓存注解，允许共享缓存名称
 * Created by wd on 2018/1/29.
 */
@Service
@CacheConfig(cacheNames = RedisCacheNames.EXAMPLE, keyGenerator = "keyGenerator")
public class RedisExampleService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Cacheable()
//    @Cacheable(key = "getName + #id.toString()")
    public String getName(String id) {
        System.out.println("进入方法了");
        return "Cacheable" + id.toString();
    }

    //    @Cacheable(key = "#id.toString()")
    @Cacheable()
    public ExampleEntity getEntity(String id) {
        System.out.println("进入方法了");
        ExampleEntity entity = new ExampleEntity();
        entity.setId(20L);
        entity.setName("example");
        entity.setAmount(BigDecimal.ONE);
        entity.setList(Arrays.asList("e1", "e2"));
        return entity;
    }


    public String getNa(Long id) {
        String value = redisTemplate.opsForValue().getAndSet(id.toString(), id.toString());
        return value;
    }
}
