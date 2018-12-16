package com.jcohy.scis.service.impl;

import com.jcohy.date.DateUtils;
import com.jcohy.scis.mapper.BkConfigMapper;
import com.jcohy.scis.model.BkConfig;
import com.jcohy.scis.model.Dept;
import com.jcohy.scis.model.Type;
import com.jcohy.scis.repository.TypeRepository;
import com.jcohy.scis.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright  : 2017- www.jcohy.com
 * Created by jiac on 23:49 2018/2/6
 * Email: jia_chao23@126.com
 * ClassName: TypeServiceImpl
 * Description:
 **/
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private BkConfigMapper bkConfigMapper;

    @Override
    public List<BkConfig> queryConfigType() {
        return bkConfigMapper.queryConfigType();
    }

    @Override
    public List<BkConfig> querySubType(String configType) {
        return bkConfigMapper.querySubType(configType);
    }
}
