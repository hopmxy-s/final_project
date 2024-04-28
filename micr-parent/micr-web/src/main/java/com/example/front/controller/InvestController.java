package com.example.front.controller;

import com.example.front.view.RespResult;
import com.example.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.common.constants.RedisKey;
import org.example.common.util.CommonUtil;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
