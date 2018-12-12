package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.ProductService;
import com.jcohy.scis.utils.JsonUtil;
import com.jcohy.scis.utils.SimpleMailSender;
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

    @Autowired
    private SimpleMailSender simpleMailSender;

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
        if (id != null){
            BkProductVo product = productService.queryById(id);
            map.put("product",product);
        }
        return "admin/product/form";
    }

    @GetMapping("/send")
    public String send(@RequestParam(required = false) Integer id,
                       @RequestParam(required = false) String productName,
                       ModelMap map){
        map.put("id",id);
        map.put("productName",productName);
        return "admin/product/send";
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

    @PostMapping("/send/to")
    @ResponseBody
    public JsonResult sendTo(BkSendReq bkSendReq){
        logger.info(bkSendReq.getBuyerEmail()+bkSendReq.getBuyerSchool()+bkSendReq.getId());
        simpleMailSender.sendText(bkSendReq.getBuyerEmail(),bkSendReq.getBuyerSchool());
       return JsonResult.ok();
    }

}
