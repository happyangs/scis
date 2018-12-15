package com.jcohy.scis.mapper;


import com.jcohy.scis.model.BkOrderReq;
import com.jcohy.scis.model.BkOrderVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BkOrderMapper {

    List<BkOrderVo> selectByCondition(BkOrderReq req);

    void insertSelective(BkOrderReq req);
}