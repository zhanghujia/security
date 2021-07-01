package com.example.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 10696
 */
@Configuration
public class Swagger3config {

    @Bean
    public Docket createRestApiFront() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("browser-api")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.securitydemo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket createRestApiBack() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .groupName("back-api")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.hc.web.controller.back"))
//                .paths(PathSelectors.any())
//                .build();
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("更多请咨询服务开发者XXX")
                .contact(new Contact("作者", "作者地址", "作者邮箱"))
                .version("1.0")
                .build();
    }

}
