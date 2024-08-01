package com.feiniaojin.ddd.hcms.domain.content;

import lombok.Getter;

@Getter
public enum TypeStatus {
    INIT(0, "初始化"),
    DRAFT(1, "草稿状态");

    private final Integer code;

    private final String name;


    TypeStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}
