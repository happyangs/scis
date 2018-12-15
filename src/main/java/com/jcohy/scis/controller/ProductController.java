package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.mapper.BkOrderMapper;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.OrderService;
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
import java.util.Date;
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

    @Autowired
    private OrderService orderService;

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

    /**
     * 发送作品
     * @param bkSendReq
     * @return
     */
    @PostMapping("/send/to")
    @ResponseBody
    public JsonResult sendTo(BkSendReq bkSendReq){
        BkProductVo bkProductVo = productService.queryById(bkSendReq.getId());
        if (bkProductVo == null){
            return JsonResult.fail("没查到作品");
        }
        SendEmailVo sendEmailVo = new SendEmailVo();
        sendEmailVo.setToEmail(bkSendReq.getBuyerEmail());
        StringBuffer sb = new StringBuffer();
        sb.append("点击下载："+bkProductVo.getLink()+" 提取码："+bkProductVo.getLinkCode());
        sendEmailVo.setContent(sb.toString());
        sendEmailVo.setSubject(bkProductVo.getProductName());
        simpleMailSender.sendText(sendEmailVo);

        // 查询今日最大订单号+1
        orderService.queryByCondition()
        // 添加订单
        Integer orderId = null;
        BkOrderReq order = new BkOrderReq();
        order.setProductId(bkProductVo.getProductId());
        order.setBuyerSchool(bkSendReq.getBuyerSchool());
        order.setBuyerEmail(bkSendReq.getBuyerEmail());
        order.setAddTime(new Date());
        order.setSendTime(new Date());
        order.setPrice(bkProductVo.getPrice());
        order.setProductType(bkProductVo.getProductType());
        order.setProductName(bkProductVo.getProductName());
        order.setOrderId(orderId);
        orderService.insert(order);

        return JsonResult.ok();
    }

}
