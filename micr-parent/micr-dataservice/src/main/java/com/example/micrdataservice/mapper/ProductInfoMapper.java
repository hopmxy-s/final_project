package com.example.micrdataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.model.ProductInfo;

import java.math.BigDecimal;
import java.util.List;

public interface ProductInfoMapper {

    /* average of interest rate*/
    BigDecimal selectAvgRate();


    /*Paging queries by product type*/
    List<ProductInfo> selectByTypeLimit(@Param("ptype") Integer ptype,
                                        @Param("offset") Integer offset,
                                        @Param("rows") Integer rows);

    /*The total number of records for a product type*/
    Integer selectCountByType(@Param("ptype") Integer pType);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);


    int updateLeftProductMoney(@Param("id") Integer productId, @Param("money") BigDecimal money);

    int updateSelled(@Param("productId") Integer productId);

    List<ProductInfo> selectFullTimeProducts(@Param("beginTime") Object beginTime, @Param("endTime") Object endTime);

    int updateStatus(@Param("id") Integer id, @Param("newStatus") int newStatus);

}