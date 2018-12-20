package com.jcohy.scis.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Bryant on 2018.12.20
 */
@AllArgsConstructor
public enum IsSuccessEnum {
    SUCCESS(0, "成功"),
    FAIL(1, "失败");

    @Getter
    private Integer code;

    @Getter
    private String description;

    public static String code2desc(Integer code) {
        for (IsSuccessEnum type : IsSuccessEnum.values()) {
            if (code.equals(type.code)) {
                return type.getDescription();
            }
        }
        return "";
    }
}
