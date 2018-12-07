package com.jcohy.scis.mapper;

import com.jcohy.scis.model.BkProductPictureReq;
import com.jcohy.scis.model.BkProductPictureVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Bryant on 2018.12.6
 */
@Component
public interface BkPictureMapper {

    List<BkProductPictureVo> selectByCondition(BkProductPictureReq bkProductPictureReq);

    void insertOrUpdate(BkProductPictureReq bkProductPictureReq);

    void deleteById(Integer id);

}
