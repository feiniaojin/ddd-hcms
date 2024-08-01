package com.feiniaojin.application.service.content.type;

import com.feiniaojin.application.service.content.type.dto.TypeCreateCommand;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityFactory;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
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
}
