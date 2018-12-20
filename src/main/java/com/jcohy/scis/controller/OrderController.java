package com.jcohy.scis.controller;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.IsSuccessEnum;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.OrderService;
import com.jcohy.scis.service.ProductService;
import com.jcohy.scis.utils.DateUtil;
import com.jcohy.scis.utils.SimpleMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Byant on 2018-11-28.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private SimpleMailSender simpleMailSender;

    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    @ResponseBody
    public PageResponse<BkOrderVo> list(@RequestParam Integer page,
                                        @RequestParam Integer limit,
                                        @RequestParam(required = false) String buyerSchool,
                                        @RequestParam(required = false) Integer productId){
        BkOrderReq bkOrderReq = new BkOrderReq();
        bkOrderReq.setPageNum(page);
        bkOrderReq.setPageSize(limit);
        bkOrderReq.setBuyerSchool(buyerSchool);
        bkOrderReq.setProductId(productId);
        PageResponse<BkOrderVo> result = orderService.queryByCondition(bkOrderReq);
        return result;
    }

    @GetMapping("/form")
    public String form(@RequestParam(required = false) Integer id,ModelMap map){
        BkOrderReq req = new BkOrderReq();
        req.setId(id);
        List<BkOrderVo> bkOrderVos = orderService.queryByCondtion(req);
        if (!CollectionUtils.isEmpty(bkOrderVos)){
            map.put("order",bkOrderVos.get(0));
        }
        return "admin/order/form";
    }

    @GetMapping("/search")
    @ResponseBody
    public PageResponse<BkOrderVo> search(@RequestBody BkOrderReq bkOrderReq){
        PageResponse<BkOrderVo> result = orderService.queryByCondition(bkOrderReq);
        return result;
    }

    @PostMapping("/save")
    @ResponseBody
    public JsonResult save(BkOrderReq bkOrderReq){
        try {
            orderService.updateById(bkOrderReq);
        } catch (Exception e) {
            logger.info("订单保存失败",e.getMessage());
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    @DeleteMapping("{id}/del")
    @ResponseBody
    public JsonResult del(@PathVariable("id") Integer id){
        try {
            orderService.deleteById(id);
        } catch (Exception e) {
            logger.warn("删除失败：{}",e);
            return JsonResult.fail(e.getMessage());
        }
        return JsonResult.ok();
    }

    /**
     * 重新发送
     * @param id
     * @return
     */
    @GetMapping("/retry/send")
    @ResponseBody
    public JsonResult sendTo(@RequestParam(required = false) Integer id){
        BkOrderReq req = new BkOrderReq();
        req.setId(id);
        List<BkOrderVo> list = orderService.queryByCondtion(req);
        if(CollectionUtils.isEmpty(list)){
            return JsonResult.fail("未找到订单");
        }
        BkOrderVo orderVo = list.get(0);
        if(orderVo.getOrderStatus().equals(IsSuccessEnum.SUCCESS.getCode())){
            return JsonResult.fail("订单已完成，请勿重复发送");
        }
        BkProductReq productReq = new BkProductReq();
        productReq.setProductId(orderVo.getProductId());
        List<BkProductVo> productVoList = productService.queryList(productReq);
        if(CollectionUtils.isEmpty(productVoList)){
            return JsonResult.fail("未找到作品信息");
        }
        BkProductVo bkProductVo = productVoList.get(0);
        if (StringUtils.isEmpty(bkProductVo.getLink()) || StringUtils.isEmpty(bkProductVo.getLinkCode())){
            return JsonResult.fail("未找到作品链接和提取码");
        }

        SendEmailVo sendEmailVo = new SendEmailVo();
        sendEmailVo.setToEmail(orderVo.getBuyerEmail());
        StringBuffer sb = new StringBuffer();
        sb.append("点击下载："+bkProductVo.getLink()+" 提取码："+bkProductVo.getLinkCode());
        sendEmailVo.setContent(sb.toString());
        sendEmailVo.setSubject(bkProductVo.getProductName());
        Integer isSuccess = simpleMailSender.sendText(sendEmailVo);

        // 更新订单
        try {
            BkOrderReq order = new BkOrderReq();
            order.setId(id);
            order.setSendTime(new Date());
            order.setUpdateTime(new Date());
            order.setSalesMan("华键");
            order.setOrderStatus(isSuccess);
            orderService.insert(order);
        } catch (Exception e) {
            logger.warn("更新订单异常",e);
        }

        if (IsSuccessEnum.FAIL.getCode().equals(isSuccess)){
            return JsonResult.fail("发送失败");
        }
        return JsonResult.ok();
    }
}
