package com.holdon.session.config;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

/**
 * Created by wd on 2018/1/24.
 */
public class RedisSessionInitializer extends AbstractHttpSessionApplicationInitializer {

    protected RedisSessionInitializer() {
        super(RedisSessionConfig.class);
    }
}
