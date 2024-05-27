package org.example.api.service;


import org.example.api.model.IncomeRecord;

import java.util.List;

public interface IncomeService {
    void generateIncomePlan();

    void generateIncomeBack();
    List<IncomeRecord> queryIncomeByUid(Integer uid, Integer pageNo, Integer pageSize);
}
