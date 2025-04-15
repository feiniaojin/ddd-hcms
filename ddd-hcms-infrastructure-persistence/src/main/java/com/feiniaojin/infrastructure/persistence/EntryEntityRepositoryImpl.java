package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.hcms.domain.content.EntryEntity;
import com.feiniaojin.ddd.hcms.domain.content.EntryEntityRepository;
import com.feiniaojin.ddd.hcms.domain.content.EntryId;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.infrastructure.persistence.data.ContentEntry;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentEntryRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class EntryEntityRepositoryImpl implements EntryEntityRepository {

    @Resource
    private ContentEntryRepository hcmsContentEntryRepository;

    @Override
    public EntryEntity load(EntryId entryEntityId) {

        ContentEntry data = hcmsContentEntryRepository.getByEntryId(entryEntityId.getValue());

        EntryEntity entity = new EntryEntity();
        entity.setEntryId(entryEntityId);
        entity.setTypeId(new TypeId(data.getTypeId()));

        entity.setId(data.getId());
        entity.setVersion(data.getVersion());
        entity.setDeleted(data.getDeleted());

        return entity;
    }

    @Override
    public void save(EntryEntity entity) {

        ContentEntry data = new ContentEntry();

        data.setId(entity.getId());
        data.setVersion(entity.getVersion());
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedTime(entity.getCreatedDate());
        data.setModifiedBy(entity.getLastModifiedBy());
        data.setModifiedTime(entity.getLastModifiedDate());
        data.setDeleted(entity.getDeleted());

        data.setTypeId(entity.getTypeId().getValue());
        data.setEntryId(entity.getEntryId().getValue());
        data.setStatus(entity.getStatus());

        hcmsContentEntryRepository.save(data);
    }
}
