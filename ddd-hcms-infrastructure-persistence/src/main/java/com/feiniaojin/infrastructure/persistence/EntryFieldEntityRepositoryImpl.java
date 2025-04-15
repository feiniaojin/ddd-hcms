package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.hcms.domain.content.*;
import com.feiniaojin.infrastructure.persistence.data.ContentEntryField;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentEntryFieldRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class EntryFieldEntityRepositoryImpl implements EntryFieldEntityRepository {

    @Resource
    private ContentEntryFieldRepository contentEntryFieldRepository;

    @Override
    public EntryFieldEntity load(EntryFieldId entityId) {

        ContentEntryField data = contentEntryFieldRepository.findOneByBizId(entityId.getValue());

        EntryFieldEntity entity = new EntryFieldEntity();
        entity.setEntryFieldId(entityId);
        entity.setEntryId(new EntryId(data.getEntryId()));
        entity.setTypeFieldId(new TypeFieldId(data.getTypeFieldId()));

        entity.setFieldName(data.getFieldName());
        entity.setFieldValue(data.getFieldValue());
        entity.setFieldDataType(data.getFieldDataType());
        entity.setStatus(data.getStatus());

        entity.setId(data.getId());
        entity.setVersion(data.getVersion());
        entity.setDeleted(data.getDeleted());

        return entity;
    }

    @Override
    public void save(EntryFieldEntity entity) {

        ContentEntryField data = new ContentEntryField();

        data.setId(entity.getId());
        data.setVersion(entity.getVersion());
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedTime(entity.getCreatedDate());
        data.setModifiedBy(entity.getLastModifiedBy());
        data.setModifiedTime(entity.getLastModifiedDate());
        data.setDeleted(entity.getDeleted());


        data.setEntryId(entity.getEntryId().getValue());
        data.setEntryFieldId(entity.getEntryFieldId().getValue());
        data.setTypeFieldId(entity.getTypeFieldId().getValue());
        data.setFieldName(entity.getFieldName());
        data.setFieldValue(entity.getFieldValue());
        data.setFieldDataType(entity.getFieldDataType());
        data.setStatus(entity.getStatus());

        contentEntryFieldRepository.save(data);
    }
}
