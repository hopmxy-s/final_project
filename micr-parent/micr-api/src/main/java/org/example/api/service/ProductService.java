package org.example.api.service;

import org.example.api.model.ProductInfo ;
import org.example.api.pojo.MultiProduct;

import java.util.List;


public interface ProductService {
    /*Query products according to product type and support paging*/
    List<ProductInfo> queryByTypeLimit(Integer pType,Integer pageNo,Integer pageSize);

    /*The total number of records for a product type*/
    Integer queryRecordNumsByType(Integer pType);

    /*Multiple product data on home page*/
    MultiProduct queryIndexPageProducts();


}
