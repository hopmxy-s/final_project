package com.example.front.controller;

import com.example.front.view.RespResult;
import com.example.front.view.recharge.ResultView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.model.RechargeRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@Api(tags = "recharge service")
@RestController
public class RechargeController extends BaseController{

    @ApiOperation(value = "search certain user recharge records")
    @GetMapping("/v1/recharge/records")
    public RespResult queryRechargePage(@RequestHeader("uid") Integer uid,
                                        @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                        @RequestParam(required = false, defaultValue = "6")Integer pageSize){
        RespResult result = RespResult.fail();
        if (uid != null && uid > 0) {
            List<RechargeRecord> records = rechargeService.queryByUid(uid, pageNo, pageSize);
            result = RespResult.ok();
            result.setList(toView(records));

        }
        return result;
    }

    private List<ResultView> toView(List<RechargeRecord> src){
        List<ResultView> target = new ArrayList<>();

        src.forEach(record -> {
            target.add(new ResultView(record));
        });
        return target;
    }
}
