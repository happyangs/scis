package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.common.interfaces.ConfigTypeEnum;
import com.jcohy.scis.mapper.BkConfigMapper;
import com.jcohy.scis.mapper.BkPictureMapper;
import com.jcohy.scis.mapper.BkProductMapper;
import com.jcohy.scis.model.*;
import com.jcohy.scis.service.PictureService;
import com.jcohy.scis.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 图片服务实现层
 * @author Bryant
 * @date 2018.12
 */
@Service
public class PictureServiceImpl implements PictureService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BkPictureMapper bkPictureMapper;

    @Autowired
    private TypeService typeService;

    @Override
    public PageResponse queryByCondition(BkProductPictureReq bkProductPictureReq) {
        List<BkProductPictureVo> list = bkPictureMapper.selectByCondition(bkProductPictureReq);
        PageHelper.startPage(bkProductPictureReq.getPageNum() , bkProductPictureReq.getPageSize());
        PageInfo<BkProductPictureVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
        if (!CollectionUtils.isEmpty(list)){
            List<BkConfig> bkConfigs = typeService.getBkConfig(ConfigTypeEnum.PICTURE_TYPE.getCode());
            Map<Integer,String> pictureTypeMap = bkConfigs.stream().filter(bkConfig -> !StringUtils.isEmpty(bkConfig.getZhName())).collect(Collectors.toMap(BkConfig::getCode,BkConfig::getZhName));

            List<BkConfig> pictureSize = typeService.getBkConfig(ConfigTypeEnum.PRODUCT_SIZE.getCode());
            Map<Integer,String> pictureSizeMap = pictureSize.stream().filter(bkConfig -> !StringUtils.isEmpty(bkConfig.getZhName())).collect(Collectors.toMap(BkConfig::getCode,BkConfig::getZhName));
            list.forEach(bkProductPictureVo -> {
                if (!CollectionUtils.isEmpty(pictureTypeMap)){
                    bkProductPictureVo.setPictureTypeName(pictureTypeMap.get(bkProductPictureVo.getPictureType()));
                    bkProductPictureVo.setPictureSizeDesc(pictureSizeMap.get(Integer.valueOf(bkProductPictureVo.getPictureSize())));
                }
            });
        }
        return PageResponse.buildSuccessResponseWithResult(list,total.intValue());
    }

    @Override
    public JsonResult insertOrUpdate(BkProductPictureReq bkProductPictureReq) {
        bkPictureMapper.insertOrUpdate(bkProductPictureReq);
        return JsonResult.ok();
    }

    @Override
    public JsonResult delete(Integer id) {
        bkPictureMapper.deleteById(id);
        return JsonResult.ok();
    }

    @Override
    public BkProductPictureVo queryById(Integer id) {
        BkProductPictureReq bkProductPictureReq = new BkProductPictureReq();
        bkProductPictureReq.setId(id);
        List<BkProductPictureVo> list = bkPictureMapper.selectByCondition(bkProductPictureReq);
        if (!CollectionUtils.isEmpty(list)){
           return list.get(0);
        }
        return null;
    }
}
