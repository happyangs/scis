package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.mapper.BkConfigMapper;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkConfigReq;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private BkConfigMapper bkConfigMapper;

    @Override
    public PageResponse queryConfig(BkConfigReq bkConfigReq) {
        List<BkConfig> list = bkConfigMapper.queryConfig(bkConfigReq);
        PageHelper.startPage(bkConfigReq.getPageNum() , bkConfigReq.getPageSize());
        PageInfo<BkConfig> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        return PageResponse.buildSuccessResponseWithResult(list,total.intValue());
    }

    @Override
    public List<BkConfig> queryConfigByCondition(BkConfigReq bkConfigReq) {
        return bkConfigMapper.queryConfig(bkConfigReq);
    }

    @Override
    public void insertOrUpdate(BkConfigReq bkConfigReq) {
        bkConfigMapper.insertOrUpdate(bkConfigReq);
    }

    @Override
    public void deleteById(Integer id) {
        bkConfigMapper.deleteById(id);
    }
}
