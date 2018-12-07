package com.jcohy.scis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.mapper.BkPictureMapper;
import com.jcohy.scis.mapper.BkProductMapper;
import com.jcohy.scis.model.BkProductPictureReq;
import com.jcohy.scis.model.BkProductPictureVo;
import com.jcohy.scis.model.BkProductVo;
import com.jcohy.scis.service.PictureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

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

    @Override
    public PageResponse queryByCondition(BkProductPictureReq bkProductPictureReq) {
        List<BkProductPictureVo> list = bkPictureMapper.selectByCondition(bkProductPictureReq);
        PageHelper.startPage(bkProductPictureReq.getPageNum() , bkProductPictureReq.getPageSize());
        PageInfo<BkProductPictureVo> pageInfo = new PageInfo<>(list);
        Long total = pageInfo.getTotal();
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
