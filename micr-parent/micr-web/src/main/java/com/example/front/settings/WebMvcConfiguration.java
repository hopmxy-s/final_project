package com.example.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

//    cross field

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("=======addCorsMappings========");
        registry.addMapping("/**")
                .allowedOriginPatterns("*")  //可跨域的域名，可以为 *
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
