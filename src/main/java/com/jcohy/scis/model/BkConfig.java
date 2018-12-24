package com.jcohy.scis.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Data
public class BkConfig implements Serializable {
    private Integer id;

    /**
     * 配置项
     */
    private String configType;

    private String configTypeDesc;

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
    private String addTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 删除标记
     */
    private Byte isDelete;
}