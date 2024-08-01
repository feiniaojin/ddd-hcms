package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
import com.feiniaojin.infrastructure.persistence.data.HcmsContentType;
import com.feiniaojin.infrastructure.persistence.jdbc.HcmsContentTypeRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TypeEntityRepositoryImpl implements TypeEntityRepository {

    @Resource
    private HcmsContentTypeRepository jdbcRepository;

    @Override
    public TypeEntity load(TypeId entityId) {

        String idValue = entityId.getValue();

        HcmsContentType hcmsContentType = jdbcRepository.findOne(idValue);

        TypeEntity typeEntity = new TypeEntity();
        typeEntity.setTypeId(entityId);
        typeEntity.setDisplayName(hcmsContentType.getDisplayName());
        typeEntity.setStatus(hcmsContentType.getStatus());

        //version id
        typeEntity.setId(hcmsContentType.getId());
        typeEntity.setVersion(hcmsContentType.getVersion());
        typeEntity.setDeleted(hcmsContentType.getDeleted());

        return typeEntity;
    }

    @Override
    @Transactional
    public void save(TypeEntity entity) {
        HcmsContentType data = new HcmsContentType();

        data.setId(entity.getId());
        data.setVersion(entity.getVersion());
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedTime(entity.getCreatedTime());
        data.setModifiedBy(entity.getModifiedBy());
        data.setModifiedTime(entity.getModifiedTime());
        data.setDeleted(entity.getDeleted());

        data.setStatus(entity.getStatus());
        data.setDisplayName(entity.getDisplayName());
        data.setContentTypeId(entity.getTypeId().getValue());

        jdbcRepository.save(data);
    }
}
