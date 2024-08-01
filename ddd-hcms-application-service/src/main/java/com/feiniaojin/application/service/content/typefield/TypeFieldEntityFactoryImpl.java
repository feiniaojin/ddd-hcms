package com.feiniaojin.application.service.content.typefield;

import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TypeFieldEntityFactoryImpl implements TypeFieldEntityFactory {

    @Override
    public TypeFieldEntity newInstance(TypeId typeEntityId, String fieldName, Integer fieldDataType) {

        TypeFieldEntity typeField = new TypeFieldEntity();
        typeField.setTypeId(typeEntityId);
        typeField.setFieldName(fieldName);
        typeField.setFieldDataType(fieldDataType);

        String string = UUID.randomUUID().toString();
        TypeFieldId typeFieldEntityId = new TypeFieldId(string);
        typeField.setTypeFieldId(typeFieldEntityId);

        typeField.setDeleted(0);

        return typeField;
    }
}
