package com.feiniaojin.ddd.hcms.domain.content;

/**
 * 数据类型的枚举
 */
public enum DataTypeEnum {
    INTEGER(0, "INT", "java.lang.Integer"),
    LONG(1, "LONG", "java.lang.Long"),
    STRING(2, "STRING", "java.lang.String");
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

    public Integer getDataType() {
        return dataType;
    }

    public String getName() {
        return name;
    }

    public String getClazz() {
        return clazz;
    }
}
