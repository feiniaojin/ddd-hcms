package com.feiniaojin.application.service.content.entry;

import com.feiniaojin.ddd.hcms.domain.content.EntryEntity;
import com.feiniaojin.ddd.hcms.domain.content.EntryEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.EntryId;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EntryEntityFactoryImpl implements EntryEntityFactory {

    @Override
    public EntryEntity newInstance(TypeId typeId) {

        EntryEntity entryEntity = new EntryEntity();

        entryEntity.setTypeId(typeId);

        String string = UUID.randomUUID().toString();
        EntryId entryEntityId = new EntryId(string);
        entryEntity.setEntryId(entryEntityId);

        entryEntity.setStatus(0);

        entryEntity.setDeleted(0);
        return entryEntity;
    }
}
