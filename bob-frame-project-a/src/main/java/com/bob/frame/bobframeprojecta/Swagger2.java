package com.bob.frame.bobframeprojecta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xp-101 on 2017/6/9.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Value("${swagger.enable}")
    private boolean swaggerEnable;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        parameterBuilder
                .parameterType("header")    //参数类型支持header, cookie, body, query etc
                .name("token")  //参数名
                .defaultValue("")  //默认值
                .description("自定义请求头Header")
                .modelRef(new ModelRef("string"))   //指定参数值的类型
                .required(false).build();   //非必需，这里是全局配置，然而在登陆的时候是不用验证的

        List<Parameter> parameters = new ArrayList<>();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerEnable)
                .globalOperationParameters(parameters)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bob.frame.bobframeprojecta.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("A工程API接口文档")
                .description("关于A工程接口使用说明")
                .version("0.0.1")
                .build();
    }

}

