package com.feiniaojin.ddd.hcms.domain.enums;

import lombok.Getter;

@Getter
public enum DeletedEnum {

    UN_DELETED(0, "正常"),
    HAVE_DELETED(1, "删除");

    private final Integer code;

    private final String name;


    DeletedEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
