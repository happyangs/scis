package com.jcohy.scis.controller;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkProductReq;
import com.jcohy.scis.model.BkProductVo;
import com.jcohy.scis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Bryant on 2018.12.3
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    @ResponseBody
    public PageResponse<BkProductVo> list(@RequestParam Integer page,
                                          @RequestParam Integer limit,
                                          @RequestParam(required = false) Integer productId){
        BkProductReq bkProductReq = new BkProductReq();
        bkProductReq.setPageNum(page);
        bkProductReq.setPageSize(limit);
        bkProductReq.setProductId(productId);
        PageResponse result = productService.queryByCondition(bkProductReq);
        return result;
    }
}
