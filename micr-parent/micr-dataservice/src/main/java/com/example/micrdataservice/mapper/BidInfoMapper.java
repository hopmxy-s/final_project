package com.example.micrdataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.model.BidInfo;
import org.example.api.pojo.BidInfoProduct;
import org.example.api.pojo.UserBidInfo;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {

//    overall amount
    BigDecimal selectSumBidMoney();

//  investment record for certain product
    List<BidInfoProduct> selectByProductId(@Param("productId") Integer productId,
                                           @Param("offset") int offset,
                                           @Param("rows") Integer rows);


    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    List<BidInfo> selectByProdId(@Param("productId") Integer productId);

    List<UserBidInfo> selectBidByUid(@Param("uid") Integer uid,
                                     @Param("offset") int offset,
                                     @Param("rows") Integer rows);
}