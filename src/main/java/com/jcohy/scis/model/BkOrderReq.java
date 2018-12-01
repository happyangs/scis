package com.jcohy.scis.model;

import com.jcohy.scis.common.PageReq;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Byant on 2018-11-30.
 */
@Data
public class BkOrderReq  extends PageReq {
    private Integer id;

    /**
     * 订单编号
     */
    private Integer orderId;

    /**
     * 作品ID
     */
    private Integer productId;

    /**
     * 作品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 作品类型 0-html 1-数据库 2-asp 3-python
     */
    private Integer productType;

    /**
     * 学校
     */
    private String buyerSchool;

    /**
     * 邮箱
     */
    private String buyerEmail;

    /**
     * 订单状态 1.交易成功 2.交易失败
     */
    private Integer orderStatus;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 备注 失败原因
     */
    private String remarks;

    /**
     * 销售员
     */
    private String salesMan;

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
}
