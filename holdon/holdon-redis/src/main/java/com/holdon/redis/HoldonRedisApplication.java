package com.holdon.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * java.lang.IllegalStateException: No cache could be resolved for 'Builder[public java.lang.String com.holdon.redis.example.RedisExampleService.getName(java.lang.Long)] caches=[] | key='' | keyGenerator='' | cacheManager='' | cacheResolver='' | condition='' | unless='' | sync='false'' using resolver 'org.springframework.cache.interceptor.SimpleCacheResolver@1866da85'. At least one cache should be provided per cache operation.
 */
@SpringBootApplication
@EnableCaching
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class HoldonRedisApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HoldonRedisApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        System.out.println("app ={}", );
    }
}
