package com.example.front.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.api.service.PlatBaseInfoService;
import org.example.api.service.ProductService;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

public class BaseController {
//    common methods, fields
    @Resource
    protected StringRedisTemplate stringRedisTemplate;

//    platform information service
    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

//    product service
    @DubboReference(interfaceClass = ProductService.class, version = "1.0")
    protected ProductService productService;
}
