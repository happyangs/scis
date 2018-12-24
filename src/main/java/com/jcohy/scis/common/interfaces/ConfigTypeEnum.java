package com.jcohy.scis.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Bryant on 2018.12.20
 */
@AllArgsConstructor
public enum ConfigTypeEnum {
    PRODUCT_TYPE("ProductType","作品类型"),
    PICTURE_TYPE("PictureType","图片类型"),
    PRODUCT_SIZE("PictureSize","图片尺寸");

    @Getter
    private String code;

    @Getter
    private String description;

    public static String code2desc(String code) {
        for (ConfigTypeEnum type : ConfigTypeEnum.values()) {
            if (code.equals(type.code)) {
                return type.getDescription();
            }
        }
        return code;
    }
}
