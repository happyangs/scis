package com.jcohy.scis.model;

import com.jcohy.scis.common.PageReq;
import lombok.Data;

import java.util.Date;

/**
 *
 * @author Bryant
 * @date 2018.12
 */
@Data
public class BkConfigReq extends PageReq {
    private Integer id;

    /**
     * 配置项
     */
    private String configType;

    /**
     * 配置code
     */
    private Integer code;

    /**
     * 内容项
     */
    private String zhName;

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
    private Byte isDelete;
}
