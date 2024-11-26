package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.EntityId;
import com.feiniaojin.ddd.hcms.domain.enums.ErrorCodeEnum;
import com.feiniaojin.ddd.hcms.domain.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

/**
 * 内容类型的ID
 *
 * @author qinyujie
 */
public class TypeId implements EntityId<String> {

    private String value;

    public TypeId(String value) {
        if (StringUtils.isBlank(value)) {
            throw new BusinessException(ErrorCodeEnum.PARAM_CANNOT_BE_NULL, "ContentTypeId");
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
