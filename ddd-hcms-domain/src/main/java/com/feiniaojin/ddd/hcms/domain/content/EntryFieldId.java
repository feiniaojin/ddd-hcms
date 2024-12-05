package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.EntityId;
import com.feiniaojin.ddd.hcms.domain.enums.ErrorCodeEnum;
import com.feiniaojin.ddd.hcms.domain.exception.BusinessException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinyujie
 */
@Data
public class EntryFieldId implements EntityId<String> {


    private String value;

    public EntryFieldId(String value) {
        if (StringUtils.isBlank(value)) {
            throw new BusinessException(ErrorCodeEnum.PARAM_CANNOT_BE_NULL, "EntryFieldEntityId");
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
