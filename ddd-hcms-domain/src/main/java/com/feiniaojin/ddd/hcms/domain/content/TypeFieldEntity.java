package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.AbstractDomainMask;
import lombok.Data;

/**
 * 内容属性实体
 *
 * @author qinyujie
 */
@Data
public class TypeFieldEntity extends AbstractDomainMask {

    private TypeFieldId typeFieldId;

    /**
     * 对于内容类型的ID
     */
    private TypeId typeId;

    private String fieldName;

    private Integer fieldDataType;

    private Integer status;
}
