package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.mapper.BkOrderMapper;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;
import com.jcohy.scis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Byant on 2018-11-30.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BkOrderMapper bkOrderMapper;

    @Override
    public PageResponse queryByCondition(BkOrderReq bkOrderReq) {
        List<BkOrderVo> list = bkOrderMapper.selectByCondition(bkOrderReq);
        PageHelper.startPage(bkOrderReq.getPageNum() , bkOrderReq.getPageSize());
        PageInfo<BkOrderVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        return PageResponse.buildSuccessResponseWithResult(list,total.intValue());
    }



    @Override
    public void insert(BkOrderReq req) {
        bkOrderMapper.insertSelective(req);
    }

    @Override
    public List<BkOrderVo> queryTodayOrders() {
        List<BkOrderVo> list = bkOrderMapper.queryTodayOrders();
        return list;
    }
}
