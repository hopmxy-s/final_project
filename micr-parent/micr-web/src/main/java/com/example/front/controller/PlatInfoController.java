package com.example.front.controller;

import com.example.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.pojo.BaseInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "platform information function")
@RequestMapping("v1")
@RestController
public class PlatInfoController extends BaseController{
//    basic information of platform
    @ApiOperation(value = "platform three basic information", notes = "register number, average interest rate, total amount")
    @GetMapping("/plat/info")
    public RespResult queryPlatBaseInfo() {
//        revoke remote services
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();


        RespResult result = new RespResult();
        result.setCode(1000); //
        result.setMsg("retrieve platform message is successful");
        result.setData(baseInfo);


        return result;
    }
}
