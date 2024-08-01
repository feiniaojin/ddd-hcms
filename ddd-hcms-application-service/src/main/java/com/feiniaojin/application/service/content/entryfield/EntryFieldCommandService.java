package com.feiniaojin.application.service.content.entryfield;

import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldCreateCommand;
import com.feiniaojin.ddd.hcms.domain.content.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EntryFieldCommandService {

    @Resource
    private EntryFieldEntityFactory factory;

    @Resource
    private EntryFieldEntityRepository repository;

    public void createEntryField(EntryFieldCreateCommand command) {

        EntryId entryEntityId = new EntryId(command.getEntryEntityId());
        TypeFieldId typeFieldEntityId = new TypeFieldId(command.getFieldEntityId());

        EntryFieldEntity entryFieldEntity = factory.newInstance(entryEntityId,
                typeFieldEntityId, command.getFieldName(), command.getFieldDataType(), command.getFieldValue());

        entryFieldEntity.doCreate();

        repository.save(entryFieldEntity);
    }
}
