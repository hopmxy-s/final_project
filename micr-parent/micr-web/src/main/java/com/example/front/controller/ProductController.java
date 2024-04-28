package com.example.front.controller;

import com.example.front.view.PageInfo;
import com.example.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.api.model.ProductInfo;
import org.example.api.pojo.MultiProduct;
import org.example.common.enums.RCode;
import org.example.common.util.CommonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags= "Function of financial products")
@RequestMapping("/v1")
@RestController
public class ProductController extends BaseController{
    @ApiOperation(value = "Home product list of three categories", notes = "one beginnerTreasure three individual and three premier products")
    @GetMapping("/product/index")
    public RespResult queryProductIndex(){
        RespResult result = RespResult.ok();

        MultiProduct multiProduct = productService.queryIndexPageProducts();
        result.setData(multiProduct);


        return result;
    }

    //Paging query by product type
    @GetMapping("/product/list")
    public RespResult queryProductByType(@RequestParam("ptype") Integer pType,
                                         @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize){
        RespResult result = RespResult.fail();
        if (pType != null && (pType == 0 || pType == 1 || pType == 2)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);

            //page process
            Integer recordNums = productService.queryRecordNumsByType(pType);
            if (recordNums > 0) {
                // product lists
                List<ProductInfo> productInfos = productService.queryByTypeLimit(pType, pageNo, pageSize);
                //build pageInfo
                PageInfo page = new PageInfo(pageNo, pageSize, recordNums);
                result = RespResult.ok();
                result.setList(productInfos);


                result.setPage(page);

            }
        }else {
            //request param is wrong
            result.setRCode(RCode.REQUEST_PRODUCT_TYPE_ERR);
        }


        return result;
    }


}
