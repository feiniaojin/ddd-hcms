package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.AbstractDomainMask;
import lombok.Data;

/**
 * @author qinyujie
 */
@Data
public class EntryEntity extends AbstractDomainMask {

    /**
     * 内容实例的唯一标识
     */
    private EntryId entryId;

    /**
     * 内容类型id
     */
    private TypeId typeId;

    /**
     * 状态
     */
    private Integer status;
}
