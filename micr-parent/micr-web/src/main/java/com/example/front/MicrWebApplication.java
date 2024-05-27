package com.example.front;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.example.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//enable dubbo services

@EnableSwaggerBootstrapUI
@EnableSwagger2
@EnableDubbo
@SpringBootApplication
public class MicrWebApplication {
    @Value("${jwt.secret}")
    private String secretKey;
    @Bean
    public JwtUtil jwtUtil(){
        JwtUtil jwtUtil = new JwtUtil(secretKey);
        return jwtUtil;
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrWebApplication.class, args);
    }

}
