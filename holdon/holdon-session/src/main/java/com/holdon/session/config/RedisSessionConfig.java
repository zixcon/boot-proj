package com.holdon.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * Created by wd on 2018/1/23.
 */
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 100)
@Configuration
public class RedisSessionConfig {

    /**
     * 推荐使用：
     * 用来定义Spring Session的 HttpSession 集成使用HTTP的头来取代使用 cookie 传送当前session信息
     *
     * @return
     */
    @Bean
    public HttpSessionStrategy headerHttpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    /**
     * 用来定义Spring Session的 HttpSession 集成使用 cookie 传送当前session信息
     *
     * @return
     */
    //@Bean
    public HttpSessionStrategy cookieHttpSessionStrategy() {
        return new CookieHttpSessionStrategy();
    }
}
