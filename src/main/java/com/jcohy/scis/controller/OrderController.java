package com.jcohy.scis.controller;

import com.jcohy.scis.common.Result;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;
import com.jcohy.scis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Byant on 2018-11-28.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/query")
    @ResponseBody
    public Result<BkOrderVo> save(@RequestBody BkOrderReq bkOrderReq){
        Result<BkOrderVo> result = orderService.queryByCondition(bkOrderReq);
        return result;
    }
}
