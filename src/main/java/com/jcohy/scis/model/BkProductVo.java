package com.jcohy.scis.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Byant on 2018-12-03
 */
@Data
public class BkProductVo implements Serializable {
    private Integer id;

    /**
     * 商品编码
     */
    private Integer productId;

    /**
     * 商品类型
     */
    private Integer productType;
    private String productTypeName;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 首页图片链接
     */
    private String showPath;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 页面张数
     */
    private Integer htmlNum;

    /**
     * 商品描述
     */
    private String productDesc;

    /**
     * 详情简介
     */
    private String productSynopsis;

    /**
     * 备注
     */
    private String remark;

    /**
     * 添加时间
     */
    private String addTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 删除标记
     */
    private Integer isDelete;

    private String isSwitch;

    private  String link;

    private String linkCode;

}