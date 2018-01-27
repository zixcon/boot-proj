package com.holdon.session.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * 注解 EnableRedisHttpSession 创建了一个名为springSessionRepositoryFilter的Spring Bean，该Bean实现了Filter接口。
 * 该filter负责通过 Spring Session 替换HttpSession从哪里返回。这里Spring Session是通过 redis 返回
 * Created by wd on 2018/1/23.
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 100)
@Configuration
public class RedisSessionConfig {

    /**
     * 推荐使用：
     * 用来定义Spring Session的 HttpSession 集成使用HTTP的头来取代使用 cookie 传送当前session信息
     * 默认名子为x-auth-token，获得权限后，需要再次js获取后再传入进来；否则会再次分配，无法获得当前session
     *
     * @return
     */
    @Bean
    public HttpSessionStrategy headerHttpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    /**
     * 用来定义Spring Session的 HttpSession 集成使用 cookie 传送当前session信息
     * 通过cookie
     *
     * @return
     */
//     @Bean
    public HttpSessionStrategy cookieHttpSessionStrategy() {
        return new CookieHttpSessionStrategy();
    }

    /**
     * 某云redis服务默认是没有起开这个功能的，需要开启这个选项；否则不加这个会报错
     *
     * @return
     */
    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory connection = new JedisConnectionFactory();
        return connection;
    }

}
