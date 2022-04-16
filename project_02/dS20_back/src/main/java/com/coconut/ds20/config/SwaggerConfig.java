package com.coconut.ds20.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                //.apis(RequestHandlerSelectors.any())
                //指定此package下的接口显示在接口文档中
                .apis(RequestHandlerSelectors.basePackage("com.coconut.ds20.controller"))
                .paths(PathSelectors.any())
                .build()
                ;
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                //文档标题
                .title("Spring Boot dS20 接口文档")
                //联系人
                .contact(new Contact("half-coconut", null, null))
                //文档描述
                .description("Spring Boot dS20 接口文档，用于描述本项目的接口信息")
                //版本
                .version("1.0")
                .build();
    }
}
