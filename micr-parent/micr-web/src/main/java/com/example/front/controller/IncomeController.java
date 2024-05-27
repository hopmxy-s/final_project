package com.example.front.controller;

import com.example.front.view.RespResult;
import com.example.front.view.income.ResultView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.model.IncomeRecord;
import org.example.api.model.RechargeRecord;
import org.example.api.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "income service")
@RestController
public class IncomeController extends BaseController{



    @ApiOperation(value = "search certain user income records")
    @GetMapping("/v1/income/records")
    public RespResult queryIncomePage(@RequestHeader("uid") Integer uid,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                        @RequestParam(required = false, defaultValue = "6")Integer pageSize){
        RespResult result = RespResult.fail();
        if (uid != null && uid > 0) {
            List<IncomeRecord> records = incomeService.queryIncomeByUid(uid, pageNo, pageSize);
            result = RespResult.ok();
            result.setList(toView(records));

        }
        return result;
    }

    private List<ResultView> toView(List<IncomeRecord> src){
        List<ResultView> target = new ArrayList<>();

        src.forEach(record -> {
            target.add(new ResultView(record));
        });
        return target;
    }
}
