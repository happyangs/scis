package com.jcohy.scis.controller;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;
import com.jcohy.scis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Byant on 2018-11-28.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

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

    @GetMapping("/search")
    @ResponseBody
    public PageResponse<BkOrderVo> search(@RequestBody BkOrderReq bkOrderReq){
        PageResponse<BkOrderVo> result = orderService.queryByCondition(bkOrderReq);
        return result;
    }
}
