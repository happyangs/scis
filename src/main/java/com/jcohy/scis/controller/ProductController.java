package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.common.interfaces.IsSuccessEnum;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.OrderService;
import com.jcohy.scis.service.ProductService;
import com.jcohy.scis.service.TypeService;
import com.jcohy.scis.utils.DateUtil;
import com.jcohy.scis.utils.SimpleMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TypeService typeService;

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
        List<BkConfig> productType = typeService.getBkConfig(ConfigTypeEnum.PRODUCT_TYPE.getCode());
        if (id != null){
            BkProductVo product = productService.queryById(id);
            map.put("product",product);
        }
        map.put("productType",productType);
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
     * 邮箱发送作品
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
        sendEmailVo.setSubject("[网页作业]"+bkProductVo.getProductName());
        Integer isSuccess = simpleMailSender.sendText(sendEmailVo);

        // 查询今日订单
        Integer orderId = null;
        List<BkOrderVo> orderList = orderService.queryTodayOrders();
        if (CollectionUtils.isEmpty(orderList)){
            orderId = Integer.valueOf(DateUtil.formatDate(new Date(),DateUtil.YYYMMDD)) * 100 + 1;
        } else {
            BkOrderVo bkOrderVo = orderList.get(0);
            orderId = bkOrderVo.getOrderId() + 1;
        }

        // 添加订单
        BkOrderReq order = new BkOrderReq();
        order.setOrderId(orderId);
        order.setProductId(bkProductVo.getProductId());
        order.setProductName(bkProductVo.getProductName());
        order.setPrice(bkProductVo.getPrice());
        order.setProductType(bkProductVo.getProductType());
        order.setBuyerSchool(bkSendReq.getBuyerSchool());
        order.setBuyerEmail(bkSendReq.getBuyerEmail());
        order.setSendTime(new Date());
        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        order.setSalesMan("华键");
        order.setOrderStatus(isSuccess);
        orderService.insert(order);

        return JsonResult.ok();
    }
}
