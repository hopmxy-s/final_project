package com.example.micrdataservice.mapper;

import org.example.api.model.BidInfo;

import java.math.BigDecimal;

public interface BidInfoMapper {

//    overall amount
    BigDecimal selectSumBidMoney();

    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);
}