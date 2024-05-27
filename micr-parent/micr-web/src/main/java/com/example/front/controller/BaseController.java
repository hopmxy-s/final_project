package com.example.front.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.api.service.*;
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

//    investment service
    @DubboReference(interfaceClass = InvestService.class, version = "1.0")
    protected InvestService investService;

    // User service
    @DubboReference(interfaceClass = UserService.class, version = "1.0")
    protected UserService userService;

    @DubboReference(interfaceClass = RechargeService.class, version = "1.0")
    protected RechargeService rechargeService;

    @DubboReference(interfaceClass = IncomeService.class, version = "1.0")
    protected IncomeService incomeService;
}
