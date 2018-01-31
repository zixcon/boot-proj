package com.holdon.user.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.springfox.SwaggerJsonSerializer;
import com.google.common.base.Charsets;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import springfox.documentation.spring.web.json.Json;

/**
 * fastjson converter
 * Created by wd on 2018/1/30.
 */
@Configuration
public class WebHttpMessageConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1. 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fastHttpMessageConverter = new FastJsonHttpMessageConverter();

        //2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
        FastJsonConfig fastJsonConfig = fastHttpMessageConverter.getFastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(Charsets.UTF_8);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");

        //3. swagger2 jackson -> fastjson
        fastJsonConfig.getSerializeConfig().put(Json.class, new SwaggerJsonSerializer());

        //4. 在converter中添加配置信息
        fastHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fastHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }
}
