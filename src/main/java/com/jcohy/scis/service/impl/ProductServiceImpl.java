package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.mapper.BkProductMapper;
import com.jcohy.scis.model.BkProductReq;
import com.jcohy.scis.model.BkProductVo;
import com.jcohy.scis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bryant on 2018.12.3
 */
@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private BkProductMapper bkProductMapper;

    @Override
    public PageResponse queryByCondition(BkProductReq bkProductReq) {
        List<BkProductVo> list = bkProductMapper.selectByCondition(bkProductReq);
        PageHelper.startPage(bkProductReq.getPageNum() , bkProductReq.getPageSize());
        PageInfo<BkProductVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        return PageResponse.buildSuccessResponseWithResult(list,total.intValue());
    }
}
