package com.jcohy.scis.model;

import com.jcohy.scis.common.PageReq;
import lombok.Data;

/**
 * Created by Bryant on 2018.12.3
 */
@Data
public class BkProductReq extends PageReq {

    /**
     * 商品编码
     */
    private Integer productId;
}
