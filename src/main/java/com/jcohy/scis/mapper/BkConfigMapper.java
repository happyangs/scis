package com.jcohy.scis.mapper;

import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.BkConfigReq;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Component
public interface BkConfigMapper {

    List<BkConfig> queryConfig(BkConfigReq bkConfigReq);

    void insertOrUpdate(BkConfigReq bkConfigReq);

    void deleteById(Integer id);
}