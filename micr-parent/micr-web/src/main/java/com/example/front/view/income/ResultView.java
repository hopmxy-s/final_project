package com.example.front.view.income;

import org.apache.commons.lang.time.DateFormatUtils;
import org.example.api.model.IncomeRecord;
import org.example.api.model.RechargeRecord;

import java.math.BigDecimal;

public class ResultView {
    private Integer id;
    private String prod_id = "Unknown";
    private String incomeDate = "-";
    private BigDecimal incomeMoney;

    public ResultView(IncomeRecord record) {
        this.id = record.getId();
        this.incomeMoney = record.getIncomeMoney();

        if (record.getIncomeDate() != null) {
            incomeDate = DateFormatUtils.format(record.getIncomeDate(), "yyyy-MM-dd");

        }

        switch (record.getProdId()) {
            case 1310694:
                prod_id = "Monthly";
                break;
            case 1310695:
                prod_id = "Quarterly";
                break;
            case 1310696:
                prod_id = "Biannual";
                break;
            case 1310697:
                prod_id = "Annual";
                break;
            case 1310699:
                prod_id = "Beginner";
                break;
            default:
                prod_id = "personal";
                break;
        }
    }

    public Integer getId() {
        return id;
    }



    public String getProdId() {
        return prod_id;
    }



    public String getIncomeDate() {
        return incomeDate;
    }



    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }


}
