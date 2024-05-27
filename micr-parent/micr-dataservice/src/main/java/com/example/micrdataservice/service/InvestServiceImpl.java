package com.example.micrdataservice.service;

import com.example.micrdataservice.mapper.BidInfoMapper;
import com.example.micrdataservice.mapper.FinanceAccountMapper;
import com.example.micrdataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.example.api.model.BidInfo;
import org.example.api.model.FinanceAccount;
import org.example.api.model.ProductInfo;
import org.example.api.pojo.BidInfoProduct;
import org.example.api.pojo.UserBidInfo;
import org.example.api.service.InvestService;
import org.example.common.constants.YLBConstant;
import org.example.common.util.CommonUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(interfaceClass = InvestService.class, version = "1.0")
public class InvestServiceImpl implements InvestService {

    @Resource
    private BidInfoMapper bidInfoMapper;

    @Resource
    private FinanceAccountMapper accountMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;

    /**

     */
    @Override
    public List<BidInfoProduct> queryBidListByProductId(Integer productId,
                                                        Integer pageNo,
                                                        Integer pageSize) {
        List<BidInfoProduct> bidList = new ArrayList<>();
        if (productId != null && productId > 0) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            bidList = bidInfoMapper.selectByProductId(productId, offset, pageSize);
        }
        return bidList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int investProduct(Integer uid, Integer productId, BigDecimal money) {
        int result = 0;
        int rows = 0;
        if ((uid != null && uid > 0) && (productId != null && productId > 0)
                && (money != null && money.intValue() % 100 == 0 && money.intValue() >= 100)) {
            FinanceAccount account = accountMapper.selectByUidForUpdate(uid);
            if (account != null) {
                if (CommonUtil.ge(account.getAvailableMoney(), money)) {
                    ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productId);
                    if (productInfo != null
                            && productInfo.getProductStatus() == YLBConstant.PRODUCT_STATUS_SELLING) {
                        if (CommonUtil.ge(productInfo.getLeftProductMoney(), money) &&
                                CommonUtil.ge(money, productInfo.getBidMinLimit()) &&
                                CommonUtil.ge(productInfo.getBidMaxLimit(), money)) {
                            rows = accountMapper.updateAvailableMoneyByInvest(uid, money);
                            if (rows < 1) {
                                throw new RuntimeException("Investment update account funds failed");
                            }
                            rows = productInfoMapper.updateLeftProductMoney(productId, money);
                            if (rows < 1) {
                                throw new RuntimeException("Failed to invest the remaining amount of product renewal");
                            }

                            BidInfo bidInfo = new BidInfo();
                            bidInfo.setBidMoney(money);
                            bidInfo.setBidStatus(YLBConstant.INVEST_STATUS_SUCC);
                            bidInfo.setBidTime(new Date());
                            bidInfo.setProdId(productId);
                            bidInfo.setUid(uid);
                            bidInfoMapper.insertSelective(bidInfo);

                            ProductInfo dbProductInfo = productInfoMapper.selectByPrimaryKey(productId);
                            if( dbProductInfo.getLeftProductMoney().compareTo(new BigDecimal("0")) == 0 ){
                                rows  = productInfoMapper.updateSelled(productId);
                                if( rows < 1 ){
                                    throw new RuntimeException("Failed to invest in updating the product to full standard");
                                }
                            }
                            result = 1;
                        }
                    } else {
                        result = 4;
                    }
                } else {
                    result = 3;
                }
            } else {
                result = 2;
            }
        }
        return result;

    }

    @Override
    public List<UserBidInfo> queryBidListByUid(Integer uid, Integer pageNo, Integer pageSize) {
        List<UserBidInfo> records = new ArrayList<>();

        if (uid != null && uid > 0) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            records = bidInfoMapper.selectBidByUid(uid, offset, pageSize);
        }

        return records;
    }
}