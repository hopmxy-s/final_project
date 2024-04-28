package com.example.micrdataservice.service;

import org.example.common.constants.PlatformConstant;
import com.example.micrdataservice.mapper.ProductInfoMapper;

import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.model.ProductInfo;
import org.example.api.pojo.MultiProduct;
import org.example.api.service.ProductService;
import org.example.common.util.CommonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = ProductService.class,version = "1.0")
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductInfoMapper productInfoMapper;

    /*Page query products by type*/
    @Override
    public List<ProductInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize) {
        List<ProductInfo> productInfos = new ArrayList<>();
        if( pType == 0 || pType == 1 || pType == 2){
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset  = (pageNo - 1) * pageSize;
            productInfos = productInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }
        return productInfos;
    }



    /*The total number of records for a product type*/
    @Override
    public Integer queryRecordNumsByType(Integer pType) {
        Integer counts = 0;
        if( pType == 0 || pType == 1 || pType == 2){
            counts  = productInfoMapper.selectCountByType(pType);
        }
        return counts;
    }

    /*Multiple product data on home page*/
    @Override
    public MultiProduct queryIndexPageProducts() {
        MultiProduct result = new MultiProduct();


        List<ProductInfo> beginnerTreasureList  = productInfoMapper.selectByTypeLimit(
                                      PlatformConstant.PRODUCT_TYPE_BEGINNERTREASURE,0,1);

        List<ProductInfo> premierList = productInfoMapper.selectByTypeLimit(
                PlatformConstant.PRODUCT_TYPE_PREMIER,0,3 );


        List<ProductInfo> individualList = productInfoMapper.selectByTypeLimit(
                PlatformConstant.PRODUCT_TYPE_INDIVIDUAL,0,3 );

        result.setBeginnerTreasure(beginnerTreasureList);
        result.setPremier(premierList);
        result.setIndividual(individualList);
        return result;
    }


}
