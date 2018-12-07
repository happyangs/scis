/*
 * Copyright (c) 2018. SheIn
 * 南京领添信息技术有限公司
 * http://shein.com
 * All rights reserved.
 */

package com.jcohy.scis.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 分页请求
 * Created by Bryant on 2018.11.14
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PageReq extends RequestVo {

    @NotNull(message = "条数不能为空")
    private Integer pageSize;

    @NotNull(message = "页码不能为空")
    private Integer pageNum;

    public PageReq(){
        super();
    }
    public PageReq(Integer pageSize,Integer pageNum){
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }
}
