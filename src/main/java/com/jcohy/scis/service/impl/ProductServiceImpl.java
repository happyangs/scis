package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.IsDeleteEnum;
import com.jcohy.scis.mapper.BkProductMapper;
import com.jcohy.scis.model.BkProductReq;
import com.jcohy.scis.model.BkProductVo;
import com.jcohy.scis.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Bryant on 2018.12.3
 */
@Service
public class ProductServiceImpl implements ProductService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Override
    public JsonResult insert(BkProductReq bkProductReq) {
        if (bkProductReq != null){
           Integer id = bkProductReq.getId();
            if(id == null){// 插入
                bkProductReq.setProductId(this.getOrderId());
                bkProductReq.setAddTime(new Date());
                bkProductMapper.insert(bkProductReq);
            }else {
                bkProductMapper.update(bkProductReq);
            }
        }
        return JsonResult.ok();
    }

    @Override
    public JsonResult delete(Integer id) {
        bkProductMapper.deleteById(id);
        return JsonResult.ok();
    }

    @Override
    public BkProductVo queryById(Integer id) {
        BkProductVo bkProductVo = new BkProductVo();
        BkProductReq bkProductReq = new BkProductReq();
        bkProductReq.setId(id);
        List<BkProductVo> list = bkProductMapper.selectByCondition(bkProductReq);
        if (!CollectionUtils.isEmpty(list)){
            bkProductVo = list.get(0);
        }
        return bkProductVo;
    }

    private Integer getOrderId(){
        Integer productId = null;
        try {
            BkProductVo bkProductVo = bkProductMapper.getMaxProductId();
            if (bkProductVo == null){
                productId = 10000;
            }else {
                productId = bkProductVo.getProductId() + 1;
            }
        } catch (Exception e) {
            logger.error("getOrderId error",e);
        }
        return productId.intValue();
    }
}
