package com.holdon.redis;

import com.alibaba.fastjson.JSON;
import com.holdon.redis.example.ExampleEntity;
import com.holdon.redis.example.RedisExampleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HoldonRedisApplicationTests {


    @Autowired
    private RedisExampleService redisExampleService;

    @Test
    public void contextLoads() {
        System.out.println(redisExampleService);
    }

    @Test
    public void testFindById() {
        String name = redisExampleService.getName("1L");
        System.out.println(name);
    }

    @Test
    public void testFindEntity() {
        ExampleEntity entity = redisExampleService.getEntity("1L");
        System.out.println(JSON.toJSONString(entity));
    }

    @Test
    public void testFindByNa() {
        String name = redisExampleService.getNa(1L);
        System.out.println(name);
    }

}
