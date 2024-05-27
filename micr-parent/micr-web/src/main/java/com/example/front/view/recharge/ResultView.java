package com.example.front.view.recharge;

import org.apache.commons.lang.time.DateFormatUtils;
import org.example.api.model.RechargeRecord;

import java.math.BigDecimal;

public class ResultView {
    private Integer id;
    private String result = "Unknown";
    private String rechargeDate = "-";
    private BigDecimal rechargeMoney;

    public ResultView(RechargeRecord record) {
        this.id = record.getId();
        this.rechargeMoney = record.getRechargeMoney();

        if (record.getRechargeTime() != null) {
            rechargeDate = DateFormatUtils.format(record.getRechargeTime(), "yyyy-MM-dd");

        }

        switch (record.getRechargeStatus()) {
            case 0:
                result = "Top-up...";
                break;
            case 1:
                result = "success";
                break;
            case 2:
                result = "fail";


        }
    }

    public Integer getId() {
        return id;
    }



    public String getResult() {
        return result;
    }



    public String getRechargeDate() {
        return rechargeDate;
    }



    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }


}
