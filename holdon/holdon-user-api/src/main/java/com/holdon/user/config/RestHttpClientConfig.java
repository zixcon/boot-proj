package com.holdon.user.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wd on 2018/2/5.
 */
@Configuration
public class RestHttpClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(this.clientHttpRequestFactory());
        FastJsonHttpMessageConverter fastHttpMessageConverter = new FastJsonHttpMessageConverter();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(fastHttpMessageConverter);
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    /**
     * 设置超时时间
     *
     * @return
     */
    private ClientHttpRequestFactory clientHttpRequestFactory() {
        //Netty4ClientHttpRequestFactory factory = new Netty4ClientHttpRequestFactory();
        //factory.setSslContext(SslContextBuilder.forClient().build());
        //HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory();
        factory.setReadTimeout(10000);
        factory.setConnectTimeout(2000);
        return factory;
    }

}
