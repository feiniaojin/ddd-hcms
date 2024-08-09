package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.AbstractDomainMask;
import com.feiniaojin.ddd.hcms.domain.enums.DeletedEnum;
import com.feiniaojin.ddd.hcms.domain.enums.PublishStatusEnum;
import lombok.Data;

/**
 * 内容类型
 *
 * @author qinyujie
 */
@Data
public class TypeEntity extends AbstractDomainMask {

    private TypeId typeId;

    private String displayName;

    private Integer status;

    public void createDraft() {
        this.status = PublishStatusEnum.DRAFT.getCode();
    }

    public void publish() {
        this.status = PublishStatusEnum.PUBLISH.getCode();
    }

    public void unPublish() {
        this.status = PublishStatusEnum.UNPUBLISH.getCode();
    }

    public void delete() {
        setDeleted(DeletedEnum.HAVE_DELETED.getCode());
    }
}
