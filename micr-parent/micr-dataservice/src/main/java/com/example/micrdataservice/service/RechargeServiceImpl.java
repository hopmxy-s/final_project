package com.example.micrdataservice.service;

import com.example.micrdataservice.mapper.RechargeRecordMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.ibatis.annotations.Param;
import org.example.api.model.RechargeRecord;
import org.example.api.service.RechargeService;
import org.example.common.util.CommonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

    @DubboService(interfaceClass = RechargeService.class, version = "1.0")
    public class RechargeServiceImpl implements RechargeService {
        @Resource
        private RechargeRecordMapper rechargeMapper;

        @Override
        public List<RechargeRecord> queryByUid(Integer uid, Integer pageNo, Integer pageSize) {
            List<RechargeRecord> records = new ArrayList<>();

            if (uid != null && uid > 0) {
                pageNo = CommonUtil.defaultPageNo(pageNo);
                pageSize = CommonUtil.defaultPageSize(pageSize);
                int offset = (pageNo - 1) * pageSize;
                records = rechargeMapper.selectByUid(uid, offset, pageSize);
            }

            return records;
        }


    }
