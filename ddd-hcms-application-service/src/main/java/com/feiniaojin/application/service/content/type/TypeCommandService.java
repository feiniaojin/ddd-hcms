package com.feiniaojin.application.service.content.type;

import com.feiniaojin.application.service.content.type.dto.TypeCreateCommand;
import com.feiniaojin.application.service.content.type.dto.TypeUpdateCommand;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.enums.TypeStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 内容类型的command 应用服务
 *
 * @author qinyujie
 */
@Service
public class TypeCommandService {

    @Resource
    private TypeEntityFactory factory;

    @Resource
    private TypeEntityRepository repository;

    public void createTypeDraft(TypeCreateCommand createCommand) {
        TypeEntity typeEntity = factory.newInstance(createCommand.getDisplayName());
        typeEntity.createDraft();
        repository.save(typeEntity);
    }

    public void updateTypeDraft(TypeUpdateCommand updateCommand) {
        TypeEntity typeEntity = repository.load(new TypeId(updateCommand.getTypeId()));
        if (TypeStatusEnum.PUBLISH.getCode().equals(typeEntity.getStatus())) {
            throw new RuntimeException("Published types cannot be updated！");
        }
        typeEntity.setDisplayName(updateCommand.getDisplayName());
        repository.save(typeEntity);
    }

    public void publish(String typeId) {
        TypeEntity typeEntity = repository.load(new TypeId(typeId));
        typeEntity.publish();
        repository.save(typeEntity);
    }

    public void unPublish(String typeId) {
        TypeEntity typeEntity = repository.load(new TypeId(typeId));
        typeEntity.unPublish();
        repository.save(typeEntity);
    }
}
