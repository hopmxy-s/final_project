package com.example.front.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigruationSettinngs {

    //Create Docket
    @Bean
    public Docket docket(){
        //1 Create Docket
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        //2 Create Api
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("HKU Project")
                .version("1.0")
                .description("Java project，using Vue，Spring Boot and Dubbo")
                .build();

        //3 ApiInfo
        docket = docket.apiInfo(apiInfo);

        //4 Set packet
        docket = docket.select().apis(RequestHandlerSelectors.
                     basePackage("com.example.front.controller")).build();

        return docket;

    }
}
