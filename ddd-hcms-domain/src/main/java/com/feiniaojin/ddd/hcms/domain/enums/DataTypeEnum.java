package com.feiniaojin.ddd.hcms.domain.enums;

import lombok.Getter;

/**
 * 数据类型的枚举
 */
@Getter
public enum DataTypeEnum {
    STRING(0, "字串", "java.lang.String"),
    TEXT(1, "文字", "java.lang.String"),
    NUMBER(2, "数字", "java.lang.String"),
    BOOLEAN(3, "是/否", "java.lang.Boolean"),
    DATE(4, "日期", "java.util.date"),
    JSON(5, "JSON", "java.lang.String"),
    EMAIL(6, "Email", "java.lang.String"),
    PASSWORD(7, "密码", "java.lang.String"),
    LINK(8, "链接", "java.lang.String");
    /**
     * 数据类型
     */
    private final Integer dataType;

    /**
     * 类型名称
     */
    private final String name;
    /**
     * 类型信息
     */
    private final String clazz;

    DataTypeEnum(Integer dataType, String name, String clazz) {
        this.dataType = dataType;
        this.name = name;
        this.clazz = clazz;
    }

}
