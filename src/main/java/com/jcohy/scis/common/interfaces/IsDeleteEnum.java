package com.jcohy.scis.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Bryant on 2018.12.4
 */
@AllArgsConstructor
public enum IsDeleteEnum {
    NOT_DELETE(0, "未删除"),
    NORMAL_DELETE(1, "正常删除");

    @Getter
    private Integer code;

    @Getter
    private String description;

    public static String code2desc(Byte code) {
        for (IsDeleteEnum type : IsDeleteEnum.values()) {
            if (code.equals(type.code)) {
                return type.getDescription();
            }
        }
        return "";
    }

}
