package com.example.front.controller;

import com.example.front.view.RespResult;
import com.example.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.model.User;
import org.example.api.pojo.UserBidInfo;
import org.example.common.constants.RedisKey;
import org.example.common.util.CommonUtil;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

// about investment

@Api(tags = "invest financial products")
@RestController
public class InvestController extends BaseController{
//  invest ranking
    @ApiOperation(value = "invest ranking", notes = "display top 3 users")
    @GetMapping("/v1/invest/rank")
    public RespResult showInvestRank() {
        //retrieve data from redis
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate
                .boundZSetOps(RedisKey.KEY_INVEST_RANK).reverseRangeWithScores(0, 2);
        List<RankView> rankList = new ArrayList<>();

        //tranverse set
        sets.forEach( tuple -> {

//            tuple.getValue(); //phone number
//            tuple.getScore();// invest amount
            rankList.add(new RankView(CommonUtil.encryPhone(tuple.getValue()), tuple.getScore()));
        });
        RespResult result = RespResult.ok();
        result.setList(rankList);
        return result;

    }


    @ApiOperation(value = "invest product")
    @PostMapping("/v1/invest/product")
    public RespResult investProduct(
            @RequestHeader("uid") Integer uid,
            @RequestParam("productId") Integer productId,
                                    @RequestParam("money")BigDecimal money){
        RespResult result = RespResult.fail();
        if ((uid != null && uid > 0) && (productId != null && productId > 0)
        && (money != null && money.intValue() % 100 == 0 && money.intValue() >= 100)){
            int investResult = investService.investProduct(uid, productId, money);
                switch (investResult){
                    case 0:
                        result.setMsg("The investment data is incorrect");
                        break;
                    case 1:
                        result = RespResult.ok();
                        modifyInvestRank(uid, money);
                        break;
                    case 2:
                        result.setMsg("The fund account does not exist");
                        break;
                    case 3:
                        result.setMsg("insufficient fund");
                        break;
                    case 4:
                        result.setMsg("Wealth management products do not exist");
                        break;



            }

        }


        return result;
    }


    // update invest ranking
    private void modifyInvestRank(Integer uid, BigDecimal money){
        User user = userService.queryById(uid);
        if (user != null) {
            String key = RedisKey.KEY_INVEST_RANK;
            stringRedisTemplate.boundZSetOps(key)
                    .incrementScore(user.getPhone(), money.doubleValue());
        }


    }

    @ApiOperation(value = "Search someone's bid record")
    @GetMapping("/v1/bid/records")
    public RespResult queryBidPage(@RequestHeader("uid") Integer uid,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                   @RequestParam(required = false, defaultValue = "6") Integer pageSize) {

        RespResult result = RespResult.fail();

        if (uid != null && uid > 0) {
            List<UserBidInfo> records = investService.queryBidListByUid(uid, pageNo, pageSize);
            result = RespResult.ok();
            result.setList(records);
        }

        return result;

    }

}
