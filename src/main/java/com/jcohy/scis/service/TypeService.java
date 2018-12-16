package com.jcohy.scis.service;

import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {

    List<BkConfig> queryConfigType();

    List<BkConfig> querySubType(String configType);
}
