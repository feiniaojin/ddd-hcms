package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.EntityId;
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
            throw new IllegalArgumentException("ContentTypeId入参不能为空");
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
