package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.EntityId;
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
            throw new IllegalArgumentException("EntryFieldEntityId入参不能为空");
        }
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
