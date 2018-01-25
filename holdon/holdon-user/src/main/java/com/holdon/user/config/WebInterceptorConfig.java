package com.holdon.user.config;

import com.holdon.user.interceptor.AuthTokenIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wd on 2018/1/25.
 */
@Configuration
public class WebInterceptorConfig {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AuthTokenIntercepter())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/login");
                super.addInterceptors(registry);
            }
        };
    }
}
