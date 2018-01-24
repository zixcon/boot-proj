package com.holdon.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * public class WebCorsConfig extends WebMvcConfigurerAdapter {
 *
 * @Override public void addCorsMappings(CorsRegistry registry) {
 * registry.addMapping("/**")
 * .allowedOrigins("*")
 * .allowCredentials(true)
 * .allowedHeaders("Origin, X-Requested-With, Content-Type, Accept")
 * .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
 * .maxAge(3600)
 * .exposedHeaders("x-auth-token")
 * ;
 * super.addCorsMappings(registry);
 * }
 * }
 * Created by wd on 2018/1/24.
 */
@Configuration
public class WebCorsConfig {

    /**
     * $.ajax({
     * type:"GET",
     * url:"http://localhost:8080/lo",
     * data:{},
     * crossDomain:true,
     * xhrFields: {  withCredentials: true  },
     * success:function(data,xhr,xhr1){
     * console.log(data); console.log(xhr1);console.log(xhr1.getResponseHeader('x-auth-token'));
     * },
     * error:function(data){
     * <p>
     * }
     * })
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .allowedHeaders("Origin, X-Requested-With, Content-Type, Accept")
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                        .exposedHeaders("x-auth-token")
                        .maxAge(3600)
                ;
                super.addCorsMappings(registry);
            }
        };
    }
}
