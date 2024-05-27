package com.example.micrdataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.model.FinanceAccount;

import java.math.BigDecimal;

public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);

    FinanceAccount selectByUidForUpdate(@Param("uid") Integer uid);

    int updateAvailableMoneyByInvest(@Param("uid") Integer uid, @Param("money") BigDecimal money);

    int updateAvailableMoneyByRecharge(@Param("uid") Integer uid, @Param("rechargeMoney") BigDecimal rechargeMoney);

    int updateAvailableMoneyByIncomeBack(@Param("uid") Integer uid,
                                         @Param("bidMoney") BigDecimal bidMoney,
                                         @Param("incomeMoney") BigDecimal incomeMoney);
}