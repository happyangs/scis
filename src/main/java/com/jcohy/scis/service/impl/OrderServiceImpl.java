package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.common.interfaces.IsSuccessEnum;
import com.jcohy.scis.mapper.BkConfigMapper;
import com.jcohy.scis.mapper.BkOrderMapper;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkConfigReq;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;
import com.jcohy.scis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Byant on 2018-11-30.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BkOrderMapper bkOrderMapper;

    @Autowired
    private BkConfigMapper bkConfigMapper;

    @Override
    public PageResponse queryByCondition(BkOrderReq bkOrderReq) {
        List<BkOrderVo> list = bkOrderMapper.selectByCondition(bkOrderReq);
        PageHelper.startPage(bkOrderReq.getPageNum() , bkOrderReq.getPageSize());
        PageInfo<BkOrderVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        if (!CollectionUtils.isEmpty(list)){
            BkConfigReq req = new BkConfigReq();
            req.setConfigType(ConfigTypeEnum.PRODUCT_TYPE.getCode());
            List<BkConfig> bkConfigs = bkConfigMapper.queryConfig(req);
            Map<Integer,String> productTypeMap = bkConfigs.stream().filter(bkConfig -> !StringUtils.isEmpty(bkConfig.getZhName())).collect(Collectors.toMap(BkConfig::getCode,BkConfig::getZhName));
            list.forEach(bkOrderVo -> {
                bkOrderVo.setOrderStatusName(IsSuccessEnum.code2desc(bkOrderVo.getOrderStatus()));
                bkOrderVo.setProductTypeName(productTypeMap.get(bkOrderVo.getProductType()));
                bkOrderVo.setAddTime(bkOrderVo.getAddTime().substring(0,bkOrderVo.getAddTime().length()-2));
            });
        }
        return PageResponse.buildSuccessResponseWithResult(list,total.intValue());
    }

    @Override
    public List<BkOrderVo> queryByCondtion(BkOrderReq bkOrderReq) {
        return bkOrderMapper.selectByCondition(bkOrderReq);
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

    @Override
    public void deleteById(Integer id) {
        bkOrderMapper.deleteById(id);
    }

    @Override
    public void updateById(BkOrderReq req) {
        if(req.getOrderStatus() == null){
            req.setOrderStatus(IsSuccessEnum.FAIL.getCode());
        }
        bkOrderMapper.updateById(req);
    }
}
