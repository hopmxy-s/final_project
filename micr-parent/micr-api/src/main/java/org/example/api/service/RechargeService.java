package org.example.api.service;

import org.example.api.model.RechargeRecord;

import java.util.List;

public interface RechargeService {
    List<RechargeRecord> queryByUid(Integer uid, Integer pageNo, Integer pageSize);

}
