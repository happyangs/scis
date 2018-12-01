package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.Meta;
import com.jcohy.scis.common.Result;
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
    public Result<BkOrderVo> queryByCondition(BkOrderReq bkOrderReq) {
        List<BkOrderVo> list = bkOrderMapper.selectByCondition(bkOrderReq);
        PageHelper.startPage(bkOrderReq.getPageNum() , bkOrderReq.getPageSize());
        PageInfo<BkOrderVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        return Result.buildResult(pageInfo.getList(), Meta.buildMeta(total.intValue()));
    }
}
