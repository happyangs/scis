package com.jcohy.scis.mapper;

import com.jcohy.scis.model.BkConfig;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BkConfigMapper {

    List<BkConfig> queryConfigType();

    List<BkConfig> querySubType(String configType);

}