package com.jcohy.scis.service;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkProductReq;

/**
 * Created by Bryant on 2018.12.3
 */
public interface ProductService {

    PageResponse queryByCondition(BkProductReq bkProductReq);
}
