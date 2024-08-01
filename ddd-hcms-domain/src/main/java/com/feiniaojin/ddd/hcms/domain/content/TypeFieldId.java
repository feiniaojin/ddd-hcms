package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.EntityId;
import org.apache.commons.lang3.StringUtils;

/**
 * @author qinyujie
 */
public class TypeFieldId implements EntityId<String> {

    private String value;

    public TypeFieldId(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("ContentFieldId入参不能为空");
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
