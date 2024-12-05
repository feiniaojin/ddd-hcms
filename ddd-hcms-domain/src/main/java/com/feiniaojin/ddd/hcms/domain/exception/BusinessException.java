package com.feiniaojin.ddd.hcms.domain.exception;

import com.feiniaojin.ddd.hcms.domain.enums.ErrorCodeEnum;
import com.feiniaojin.gracefulresponse.GracefulResponse;

/**
 * @version 1.0
 * @Author kezry
 * @Date 2024/11/26 20:30
 */
public class BusinessException extends RuntimeException {
    private final String code;
    private static final String COLON = ":";

    public BusinessException(String message) {
        this("500", message);
    }
    public BusinessException(ErrorCodeEnum codeEnum) {
        this(String.valueOf(codeEnum.getCode()), codeEnum.getDesc());
    }
    public BusinessException(ErrorCodeEnum codeEnum, String desc) {
        this(String.valueOf(codeEnum.getCode()), String.format("%s%s%s",codeEnum.getDesc(), COLON, desc));
    }
    public BusinessException(String desc, ErrorCodeEnum codeEnum) {
        this(String.valueOf(codeEnum.getCode()), String.format("%s%s%s",desc, COLON, codeEnum.getDesc()));
    }

    public BusinessException(String message, Throwable e) {
        this("500", message, e);
    }

    public BusinessException(String code, String message) {
        this(code, message, (Throwable)null);
    }

    public BusinessException(String code, String message, Throwable e) {
        this.code = code;
        GracefulResponse.raiseException(code, message, e);
    }

    public String getCode() {
        return this.code;
    }
}
