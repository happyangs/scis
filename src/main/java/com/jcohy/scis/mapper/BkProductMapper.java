package com.jcohy.scis.mapper;


import com.jcohy.scis.model.BkProduct;
import org.springframework.stereotype.Component;

@Component
public interface BkProductMapper {

    BkProduct selectByPrimaryKey(Integer id);
}