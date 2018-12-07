package com.jcohy.scis.service;

import com.jcohy.scis.common.JsonResult;
import com.jcohy.scis.common.PageResponse;
import com.jcohy.scis.model.BkProductPictureReq;
import com.jcohy.scis.model.BkProductPictureVo;

/**
 * 图片服务
 * @author Bryant
 * @date 2018.12
 */
public interface PictureService {

    PageResponse queryByCondition(BkProductPictureReq bkProductPictureReq);

    JsonResult insertOrUpdate(BkProductPictureReq bkProductPictureReq);

    JsonResult delete(Integer id);

    BkProductPictureVo queryById(Integer id);
}
