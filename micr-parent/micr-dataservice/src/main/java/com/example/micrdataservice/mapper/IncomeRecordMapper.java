package com.example.micrdataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.model.IncomeRecord;

import java.util.Date;
import java.util.List;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);

    List<IncomeRecord> selectExpiredIncome(@Param("expiredDate") Date expiredDate);

    List<IncomeRecord> selectIncomeByUid(@Param("uid") Integer uid, @Param("offset") int offset, @Param("rows") Integer rows);
}