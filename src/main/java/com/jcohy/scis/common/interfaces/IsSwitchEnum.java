package com.jcohy.scis.common.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Byant on 2018-12-22.
 */
@AllArgsConstructor
public enum IsSwitchEnum {
    OPEN(0, "开"),
    CLOSE(1, "关");

    @Getter
    private Integer code;

    @Getter
    private String description;

    public static String code2desc(Integer code) {
        for (IsSwitchEnum type : IsSwitchEnum.values()) {
            if (code.equals(type.code)) {
                return type.getDescription();
            }
        }
        return "";
    }
}