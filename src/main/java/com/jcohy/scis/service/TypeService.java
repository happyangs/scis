package com.jcohy.scis.service;

import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkConfigReq;
import com.jcohy.scis.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    PageResponse queryConfig(BkConfigReq bkConfigReq);

    List<BkConfig> queryConfigByCondition(BkConfigReq bkConfigReq);

    void insertOrUpdate(BkConfigReq bkConfigReq);

    void deleteById(Integer id);
}
