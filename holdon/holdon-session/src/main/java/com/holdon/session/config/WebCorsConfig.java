package com.holdon.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
     * url:"http://localhost:8080/ses",
     * beforeSend: function(xhr) {
     * xhr.setRequestHeader("x-auth-token");
     * },
     * headers:{"x-auth-token":"b094c3a1-5486-4c8b-9cf2-c5cfd9d45edf"},
     * data:{},
     * crossDomain:true,
     * xhrFields: {  withCredentials: true  },
     * success:function(data,status,xhr){
     * console.log(data); console.log(status);console.log(xhr.getResponseHeader('x-auth-token'));
     * },
     * error:function(data){
     * <p>
     * }
     * })
     *
     * @return
     */
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowCredentials(true)
//                        .allowedHeaders("Origin, X-Requested-With, Content-Type, Accept")
//                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
//                        .exposedHeaders("x-auth-token")
//                        .maxAge(3600)
//                ;
//                super.addCorsMappings(registry);
//            }
//        };
//    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
