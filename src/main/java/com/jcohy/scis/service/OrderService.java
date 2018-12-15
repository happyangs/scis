package com.jcohy.scis.service;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;

import java.util.List;

/**
 * Created by Byant on 2018-11-30.
 */
public interface OrderService {

    PageResponse<BkOrderVo> queryByCondition(BkOrderReq bkOrderReq);

    void insert(BkOrderReq req);

    List<BkOrderVo> queryTodayOrders();
}
