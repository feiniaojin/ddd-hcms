package com.feiniaojin.application.service.content.type;

import com.feiniaojin.application.service.content.type.dto.TypeView;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * CQRS 调用mapper完成数据查询
 *
 * @author qinyujie
 */
@Service
public class TypeQueryService {

    @Resource
    private TypeEntityRepository repository;

    public TypeView findOne(String id) {
        TypeEntity typeEntity = repository.load(new TypeId(id));
        return this.translate(typeEntity);
    }

    private TypeView translate(TypeEntity typeEntity) {

        TypeView typeView = new TypeView();
        typeView.setTypeEntityId(typeEntity.getTypeId().getValue());
        typeView.setDisplayName(typeEntity.getDisplayName());
        return typeView;
    }
}
