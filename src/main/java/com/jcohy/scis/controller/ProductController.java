package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.ProductService;
import com.jcohy.scis.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryant on 2018.12.3
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id, ModelMap map){
        BkProductVo product = new BkProductVo();
        productService.queryById(id);
        map.put("product",product);

        return "product/form";
    }


    @PostMapping("/insert")
    @ResponseBody
    public JsonResult saveOrUpdate(BkProductReq bkProductReq){
        productService.insert(bkProductReq);
        return JsonResult.ok();
    }

    @GetMapping("/del")
    @ResponseBody
    public JsonResult delete(@RequestParam(required = false) Integer id){
        return productService.delete(id);
    }

}
