package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.AbstractDomainMask;
import com.feiniaojin.ddd.hcms.domain.enums.DeletedEnum;
import com.feiniaojin.ddd.hcms.domain.enums.PublishStatusEnum;
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

    public void delete() {
        setDeleted(DeletedEnum.HAVE_DELETED.getCode());
    }

    public void release() {
        this.status = PublishStatusEnum.PUBLISH.getCode();
    }

    public void unRelease() {
        this.status = PublishStatusEnum.UNPUBLISH.getCode();
    }
}
