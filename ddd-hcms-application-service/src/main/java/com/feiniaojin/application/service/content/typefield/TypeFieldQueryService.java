package com.feiniaojin.application.service.content.typefield;

import com.feiniaojin.application.service.content.typefield.dto.TypeFiledView;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldEntityRepository;
import com.feiniaojin.ddd.hcms.domain.content.TypeFieldId;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeFieldQueryService {

    @Resource
    private TypeFieldEntityRepository repository;

    public TypeFiledView findOne(String fieldId) {
        TypeFieldEntity entity = repository.load(new TypeFieldId(fieldId));
        return translate(entity);
    }

    public List<TypeFiledView> findList(String typeId) {
        List<TypeFieldEntity> entityList = repository.findByTypeId(typeId);
        return translate(entityList);
    }

    private TypeFiledView translate(TypeFieldEntity entity) {
        TypeFiledView view = new TypeFiledView();
        view.setFieldId(entity.getTypeFieldId().getValue());
        view.setTypeId(entity.getTypeId().getValue());
        view.setFieldName(entity.getFieldName());
        view.setFieldDataType(entity.getFieldDataType());
        return view;
    }

    private List<TypeFiledView> translate(List<TypeFieldEntity> entityList) {
        return entityList.stream().map(this::translate).toList();
    }
}
