package com.jcohy.scis.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Bryant on 2018.12.6
 */
@Data
public class BkProductPictureVo implements Serializable {

    private Integer id;

    /**
     * 商品编码
     */
    private Integer productId;

    /**
     * 图片类型 0-其他 1-首页 2-尾页 3-展示页
     */
    private Integer pictureType;
    private String pictureTypeName;

    /**
     * 图片尺寸
     */
    private String pictureSize;

    /**
     * 图片链接
     */
    private String picturePath;

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
}
