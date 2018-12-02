package com.jcohy.scis.service;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;

/**
 * Created by Byant on 2018-11-30.
 */
public interface OrderService {

    PageResponse<BkOrderVo> queryByCondition(BkOrderReq bkOrderReq);
}
