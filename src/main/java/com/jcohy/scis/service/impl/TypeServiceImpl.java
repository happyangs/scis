package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.mapper.BkConfigMapper;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkConfigReq;
import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (!CollectionUtils.isEmpty(list)){
            list.forEach(bkConfig -> {
                bkConfig.setConfigType(ConfigTypeEnum.code2desc(bkConfig.getConfigType()));
                bkConfig.setAddTime(bkConfig.getAddTime().substring(0,bkConfig.getAddTime().length()-2));
                bkConfig.setUpdateTime(bkConfig.getUpdateTime().substring(0,bkConfig.getUpdateTime().length()-2));
                }
            );
        }
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

    @Override
    public Map<Integer,String> getConfigMap(String configType){
        BkConfigReq req = new BkConfigReq();
        req.setConfigType(configType);
        List<BkConfig> bkConfigs = bkConfigMapper.queryConfig(req);
        Map<Integer,String> map = bkConfigs.stream().filter(bkConfig -> !StringUtils.isEmpty(bkConfig.getZhName())).collect(Collectors.toMap(BkConfig::getCode,BkConfig::getZhName));
        return map;
    }

    @Override
    public List<BkConfig> getBkConfig(String configType){
        BkConfigReq req = new BkConfigReq();
        req.setConfigType(configType);
        List<BkConfig> bkConfigs = bkConfigMapper.queryConfig(req);
        return bkConfigs;
    }
}
