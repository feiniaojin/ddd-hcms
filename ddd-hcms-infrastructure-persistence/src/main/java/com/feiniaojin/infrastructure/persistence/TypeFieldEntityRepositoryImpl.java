package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntityRepository;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldId;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.infrastructure.persistence.data.ContentTypeField;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeFieldRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeFieldEntityRepositoryImpl implements TypeFieldEntityRepository {

    @Resource
    private ContentTypeFieldRepository hcmsContentTypeFieldRepository;

    @Override
    public TypeFieldEntity load(TypeFieldId entityId) {

        ContentTypeField data = hcmsContentTypeFieldRepository.findOneByBizId(entityId.getValue());

        TypeFieldEntity entity = new TypeFieldEntity();

        entity.setStatus(data.getStatus());
        entity.setFieldName(data.getFieldName());
        entity.setTypeFieldId(entityId);
        entity.setFieldDataType(data.getFieldDataType());
        entity.setTypeId(new TypeId(data.getTypeId()));

        //version id
        entity.setId(data.getId());
        entity.setVersion(data.getVersion());
        entity.setDeleted(data.getDeleted());

        return entity;
    }

    @Override
    public void save(TypeFieldEntity entity) {
        ContentTypeField data = new ContentTypeField();

        data.setId(entity.getId());
        data.setVersion(entity.getVersion());
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedTime(entity.getCreatedDate());
        data.setModifiedBy(entity.getLastModifiedBy());
        data.setModifiedTime(entity.getLastModifiedDate());
        data.setDeleted(entity.getDeleted());

        data.setFieldName(entity.getFieldName());
        data.setFieldDataType(entity.getFieldDataType());
        data.setStatus(entity.getStatus());
        data.setFieldId(entity.getTypeFieldId().getValue());
        data.setTypeId(entity.getTypeId().getValue());

        hcmsContentTypeFieldRepository.save(data);
    }

    @Override
    public List<TypeFieldEntity> findByTypeId(String typeId) {
        List<ContentTypeField> dataList = hcmsContentTypeFieldRepository.findByTypeId(typeId);
        return translate(dataList);
    }

    private List<TypeFieldEntity> translate(List<ContentTypeField> dataList) {
        return dataList.stream().map(data -> {
            TypeFieldEntity entity = new TypeFieldEntity();
            entity.setStatus(data.getStatus());
            entity.setFieldName(data.getFieldName());
            entity.setTypeFieldId(new TypeFieldId(data.getFieldId()));
            entity.setFieldDataType(data.getFieldDataType());
            entity.setTypeId(new TypeId(data.getTypeId()));
            return entity;
        }).toList();
    }
}
