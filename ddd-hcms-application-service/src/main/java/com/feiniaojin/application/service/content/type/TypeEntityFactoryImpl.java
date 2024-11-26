package com.feiniaojin.application.service.content.type;

import com.feiniaojin.ddd.hcms.domain.exception.BusinessException;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.enums.DeletedEnum;
import com.feiniaojin.ddd.hcms.domain.enums.ErrorCodeEnum;
import com.feiniaojin.ddd.hcms.domain.enums.PublishStatusEnum;
import com.feiniaojin.infrastructure.persistence.data.ContentType;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author qinyujie
 */
@Service
public class TypeEntityFactoryImpl implements TypeEntityFactory {
    @Resource
    private ContentTypeRepository jdbcEntryRepository;

    @Override
    public TypeEntity newInstance(String displayName) {

        ContentType entryData = jdbcEntryRepository.getByDisplayName(displayName);
        if (entryData != null) {
            throw new BusinessException(ErrorCodeEnum.PARAM_DUPLICATE, "displayName");
        }

        TypeEntity typeEntity = new TypeEntity();
        String idStr = UUID.randomUUID().toString();
        TypeId typeEntityId = new TypeId(idStr);

        typeEntity.setTypeId(typeEntityId);
        typeEntity.setDisplayName(displayName);
        typeEntity.setStatus(PublishStatusEnum.DRAFT.getCode());

        typeEntity.setDeleted(DeletedEnum.UN_DELETED.getCode());
        return typeEntity;
    }
}
