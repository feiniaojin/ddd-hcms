package com.feiniaojin.application.service.content.typefield;

import com.feiniaojin.application.service.content.typefield.dto.TypeFieldCreateCommand;
import com.feiniaojin.application.service.content.typefield.dto.TypeFieldUpdateCommand;
import com.feiniaojin.ddd.hcms.domain.content.*;
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

    public void updateTypeField(TypeFieldUpdateCommand command) {
        TypeFieldEntity entity = repository.load(new TypeFieldId(command.getFieldId()));
        entity.setTypeId(new TypeId(command.getTypeId()));
        entity.setFieldName(command.getFieldName());
        entity.setFieldDataType(command.getFieldDataType());
        repository.save(entity);
    }

    public void delete(String fieldId) {
        TypeFieldEntity entity = repository.load(new TypeFieldId(fieldId));
        entity.delete();
        repository.save(entity);
    }

    public void release(String fieldId) {
        TypeFieldEntity entity = repository.load(new TypeFieldId(fieldId));
        entity.release();
        repository.save(entity);
    }

    public void unRelease(String fieldId) {
        TypeFieldEntity entity = repository.load(new TypeFieldId(fieldId));
        entity.unRelease();
        repository.save(entity);
    }
}
