package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.AbstractDomainMask;
import lombok.Data;

/**
 * @author qinyujie
 */
@Data
public class EntryFieldEntity extends AbstractDomainMask {

    /**
     * 实例属性唯一标识
     */
    private EntryFieldId entryFieldId;

    /**
     * 属性定义标识
     */
    private TypeFieldId typeFieldId;

    /**
     * 实例的id
     */
    private EntryId entryId;

    private String fieldName;

    private Integer fieldDataType;

    private String fieldValue;

    private Integer status;

    public void doCreate() {
        // todo 业务初始化逻辑
    }
}
