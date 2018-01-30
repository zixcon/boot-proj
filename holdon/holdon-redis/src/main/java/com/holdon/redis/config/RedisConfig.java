package com.holdon.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * https://docs.spring.io/spring-data/redis
 * 注解开启cglib代理,开启 exposeProxy = true,暴露代理对象 (否则AopContext.currentProxy()) 会抛出异常
 * Created by wd on 2018/1/27.
 */
@Configuration
public class RedisConfig {

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public RedisConnectionFactory redisConnectionFactory() {
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

//    @Bean
//    @ConditionalOnClass(Gson.class)
//    public Gson gson() {
//        GsonBuilder gsonBuilder = new GsonBuilder()
//                .setPrettyPrinting()
//                //智能null,支持输出值为null的属性
//                .serializeNulls()
//                .setDateFormat("yyyy-MM-dd HH:mm:ss")
//                .enableComplexMapKeySerialization()
//                //不序列化与反序列化没有@Expose标注的字段
//                .excludeFieldsWithoutExposeAnnotation();
//        return gsonBuilder.create();
//    }

//    @Bean
//    @ConditionalOnClass(StringRedisTemplate.class)
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory, Gson gson) {
//        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
////        TypeToken<Object> typeToken = new TypeToken<Object>() {
////        };
////        Gson2RedisSerializer gson2RedisSerializer = new Gson2RedisSerializer(gson, Object.class);
//        Gson2RedisSerializer gson2RedisSerializer = new Gson2RedisSerializer(gson);
//        template.setValueSerializer(gson2RedisSerializer);
//        template.setHashValueSerializer(gson2RedisSerializer);
//
//        return template;
//    }


    /**
     * 1. 源码中通过enableDefaultSerializer控制是否启用默认的序列化工具，
     * 默认为jdk，所以存在key为：\xac\xed\x00\x05t\x00的情况
     * <p>
     * public void afterPropertiesSet() {
     * super.afterPropertiesSet();
     * boolean defaultUsed = false;
     * if(this.defaultSerializer == null) {
     * this.defaultSerializer = new JdkSerializationRedisSerializer(this.classLoader != null?this.classLoader:this.getClass().getClassLoader());
     * }
     * <p>
     * if(this.enableDefaultSerializer) {
     * if(this.keySerializer == null) {
     * this.keySerializer = this.defaultSerializer;
     * defaultUsed = true;
     * }
     * ...
     * <p>
     * 2.替换RedisAutoConfiguration在jedis启动时会自动加载的stringRedisTemplate实例
     * 更改值的序列化机制为json序列化
     *
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    @ConditionalOnClass(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
        // explicitly enable transaction support
        // template.setEnableTransactionSupport(true);
        FastJson2RedisSerializer fastJson2RedisSerializer = new FastJson2RedisSerializer(Object.class);
        template.setValueSerializer(fastJson2RedisSerializer);
        template.setHashValueSerializer(fastJson2RedisSerializer);
        return template;
    }


    /**
     * 1. spring cache 和 redis 集成。
     * 2. 存在key为：\xac\xed\x00\x05t\x00的情况?
     * 2.1 由于RedisAutoConfiguration在jedis启动时会自动加载redisTemplate 和 stringRedisTemplate两个实例
     * 2.2 这里参数注入的如果是redisTemplate，则使用默认的jdk序列化机制
     *
     * @param stringRedisTemplate
     * @return
     */
    @Bean
    public CacheManager redisCacheManager(StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(stringRedisTemplate);
        // 存在多个缓存时，需要设置名称区分
        Map<String, Long> expires = new HashMap<>();
        expires.put(RedisCacheNames.CONFIG, 24 * 60 * 60L);
        expires.put(RedisCacheNames.EXAMPLE, 60L);
        redisCacheManager.setExpires(expires);
        redisCacheManager.setCacheNames(Arrays.asList(RedisCacheNames.CONFIG, RedisCacheNames.EXAMPLE));
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
                builder.append("|");
                builder.append(obj.toString());
            }
            return builder.toString();
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

//    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer() {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        return jackson2JsonRedisSerializer;
//    }


}
