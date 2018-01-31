package com.holdon.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wd on 2018/1/30.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.holdon.user.controller";
    public static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi() {
        // 增加全局请求头参数
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("x-auth-token")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        List<Parameter> pars = new ArrayList<>();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enableSwagger)
                // base，最终调用接口后会和paths拼接在一起
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                //过滤的接口
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")
                .description("This is to show api description")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("http://localhost:8080/v2/api-docs")
                .version(VERSION)
                .contact(new Contact("zixcon", "http://localhost:8080", "zixcon.fan@outlook.com"))
                .build();
    }
}
