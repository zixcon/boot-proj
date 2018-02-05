package com.holdon.user.config;

import com.holdon.user.interceptor.AuthTokenIntercepter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by wd on 2018/1/25.
 */
@Configuration
public class WebInterceptorConfig {

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
        return new WebMvcConfigurerAdapter() {
            /**
             * 拦截器
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new AuthTokenIntercepter())
                        .addPathPatterns("/**")
                        .excludePathPatterns(
                                "/swagger-ui.html",
                                "/webjars/**",
                                "/v2/api-docs",
                                "/swagger-resources/**",
                                "/login",
                                "/wx/auth/login",
                                "/wx/auth/jscode",
                                "/user/register");
                super.addInterceptors(registry);
            }

            /**
             * 静态资源
             * @param registry
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");

                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
                super.addResourceHandlers(registry);
            }

            /**
             * 页面跳转
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/toError").setViewName("error");
                super.addViewControllers(registry);
            }
        };
    }
}
