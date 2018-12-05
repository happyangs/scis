package com.jcohy.scis.mapper;

import com.jcohy.scis.model.BkProductReq;
import com.jcohy.scis.model.BkProductVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bryant on 2018.12.3
 */
@Component
public interface BkProductMapper {

    List<BkProductVo> selectByCondition(BkProductReq bkProductReq);

    void insert(BkProductReq bkProductReq);

    BkProductVo getMaxProductId();

    void deleteById(Integer id);

    void update(BkProductReq bkProductReq);
}
