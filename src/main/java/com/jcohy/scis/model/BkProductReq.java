package com.jcohy.scis.model;

import com.jcohy.scis.common.PageReq;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Bryant on 2018.12.3
 */
@Data
public class BkProductReq extends PageReq {

    private Integer id;
    /**
     * 商品编码
     */
    private Integer productId;

    /**
     * 商品类型
     */
    private Integer productType;

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
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标记
     */
    private Integer isDelete;

    /**
     * 开关
     */
    private String iSwitch;

    private String link;

    private String linkCode;

    public BkProductReq(){
        super();
    }
    public BkProductReq(Integer page, Integer limit) {
        super(page,limit);

    }
}
