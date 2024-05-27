package org.example.api.service;

import org.example.api.pojo.BidInfoProduct;
import org.example.api.pojo.UserBidInfo;

import java.math.BigDecimal;
import java.util.List;

public interface InvestService {

    // search investment records for certain products
    List<BidInfoProduct> queryBidListByProductId(Integer productId,
                                                 Integer pageNo,
                                                 Integer pageSize);

    int investProduct(Integer uid, Integer productId, BigDecimal money);
    /*Query of Invest record of one user*/
    List<UserBidInfo> queryBidListByUid(Integer uid, Integer pageNo, Integer pageSize);
}
