package com.feiniaojin.application.service.content.typefield;

import com.feiniaojin.application.service.content.typefield.dto.TypeFieldCreateCommand;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TypeFieldCommandService {

    @Resource
    private TypeFieldEntityRepository repository;

    @Resource
    private TypeFieldEntityFactory factory;

    public void createTypeField(TypeFieldCreateCommand createCommand) {

        TypeId typeEntityId = new TypeId(createCommand.getTypeId());

        TypeFieldEntity typeField = factory.newInstance(typeEntityId,
                createCommand.getFieldName(),
                createCommand.getFieldDataType());

        repository.save(typeField);
    }
}
