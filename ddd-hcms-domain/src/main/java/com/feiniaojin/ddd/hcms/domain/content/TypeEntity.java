package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.AbstractDomainMask;
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
        this.status = TypeStatus.DRAFT.getCode();
    }
}
