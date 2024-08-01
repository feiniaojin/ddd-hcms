package com.feiniaojin.application.service.content.entry;

import com.feiniaojin.application.service.content.entry.dto.EntryCreateCommand;
import com.feiniaojin.ddd.hcms.domain.content.EntryEntity;
import com.feiniaojin.ddd.hcms.domain.content.EntryEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.EntryEntityRepository;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class EntryCommandService {

    @Resource
    private EntryEntityFactory factory;

    @Resource
    private EntryEntityRepository repository;

    public void createEntry(EntryCreateCommand command) {

        TypeId typeEntityId = new TypeId(command.getTypeEntityId());

        EntryEntity entryEntity = factory.newInstance(typeEntityId);

        repository.save(entryEntity);
    }
}
