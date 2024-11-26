package com.feiniaojin.ddd.hcms.domain.enums;

import lombok.Getter;

/**
 * 错误代码枚举
 * @version 1.0
 * @Author kezry
 * @Date 2024/11/26 20:30
 */

@Getter
public enum ErrorCodeEnum {
    // 1000 变量错误
    PARAM_CANNOT_BE_NULL(1000, "入参不能为空"),
    PARAM_DUPLICATE(1001, "重复的参数"),

    // 2000 运行时错误

    // 3000 资源错误
    RETURN_PARAMETER_IS_NULL(3000, "返回参数为空"),
    PARAMETERS_CANNOT_BE_UPDATED(3001, "已发布的参数不能更新"),
    PARAMETERS_CANNOT_BE_DELETE(3002, "已发布的参数不能删除")

    // 4000 逻辑错误

    ;
    /**
     * 错误代码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String desc;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
