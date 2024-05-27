package com.example.micrdataservice.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.api.model.RechargeRecord;

import java.util.List;

public interface RechargeRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RechargeRecord record);

    int insertSelective(RechargeRecord record);

    RechargeRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RechargeRecord record);

    int updateByPrimaryKey(RechargeRecord record);

    List<RechargeRecord> selectByUid(@Param("uid") Integer uid, @Param("offset") Integer offset, @Param("rows") Integer rows);
}