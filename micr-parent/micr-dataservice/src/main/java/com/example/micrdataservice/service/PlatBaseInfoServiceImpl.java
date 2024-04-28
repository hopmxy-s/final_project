package com.example.micrdataservice.service;

import com.example.micrdataservice.mapper.BidInfoMapper;
import com.example.micrdataservice.mapper.ProductInfoMapper;
import com.example.micrdataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.pojo.BaseInfo;
import org.example.api.service.PlatBaseInfoService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class, version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    //inject mappers
    @Resource
    private UserMapper userMapper;
    @Resource
    private BidInfoMapper bidInfoMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;

//    platform basic information
    @Override
    public BaseInfo queryPlatBaseInfo() {
//        obtain register number of users, average profit rate, overall amount
        int registerUser = userMapper.selectCountUsers();
//      average profit rate
        BigDecimal avgRate = productInfoMapper.selectAvgRate();
//      overall amount
        BigDecimal sumBidMoney = bidInfoMapper.selectSumBidMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate, sumBidMoney, registerUser);
        return baseInfo;
    }
}
