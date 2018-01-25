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
     *
     * 无法支持全局跨域访问，不知道为什么
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

    /**
     * $.ajax({
     * type:"GET",
     * url:"http://127.0.0.1:8080/lo",
     * beforeSend: function(xhr) {
     * xhr.setRequestHeader('x-auth-token',"98e15fc0-d4ec-49b6-a897-69ad7edfdc43");
     * },
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
     * 支持全局跨域访问
     * 由于CorsFilter是定义在Web容器中的过滤器（实现了javax.servlet.Filter），因此其执行顺序先于Servlet，
     * 而SpringMVC的入口是DispatchServlet，因此该Filter会先于SpringMVC的所有拦截器执行。
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addExposedHeader("x-auth-token");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
