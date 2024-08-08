package com.feiniaojin.application.service.content.type;

import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.enums.TypeStatusEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author qinyujie
 */
@Service
public class TypeEntityFactoryImpl implements TypeEntityFactory {

    @Override
    public TypeEntity newInstance(String displayName) {

        TypeEntity typeEntity = new TypeEntity();
        String idStr = UUID.randomUUID().toString();
        TypeId typeEntityId = new TypeId(idStr);

        typeEntity.setTypeId(typeEntityId);
        typeEntity.setDisplayName(displayName);
        typeEntity.setStatus(TypeStatusEnum.DRAFT.getCode());

        typeEntity.setDeleted(0);
        return typeEntity;
    }
}
