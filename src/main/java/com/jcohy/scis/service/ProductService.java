package com.jcohy.scis.service;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkProductReq;
import com.jcohy.scis.model.BkProductVo;

import java.util.List;

/**
 * Created by Bryant on 2018.12.3
 */
public interface ProductService {

    PageResponse queryByCondition(BkProductReq bkProductReq);

    List<BkProductVo> queryList(BkProductReq bkProductReq);

    JsonResult insert(BkProductReq bkProductReq);

    JsonResult delete(Integer id);

    BkProductVo queryById(Integer id);

}
