package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.EntityId;
import com.feiniaojin.ddd.hcms.domain.file.FilesEntity;
import com.feiniaojin.ddd.hcms.domain.file.FilesEntityRepository;
import com.feiniaojin.infrastructure.persistence.data.Files;
import com.feiniaojin.infrastructure.persistence.jdbc.FilesRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Repository
public class FilesEntityRepositoryImpl implements FilesEntityRepository {

@Resource
private FilesRepository jdbcFilesRepository;

    @Override
    public FilesEntity load(EntityId entityId) {

//        Files data = jdbcFilesRepository.findByBizId(entityId.getValue());
        Files data = new Files();

        if (Objects.isNull(data)) {
            return null;
        }

        FilesEntity entity = new FilesEntity();
        //todo 业务字段

        //元数据
        entity.setId(data.getId());
        entity.setCreatedBy(data.getCreatedBy());
        entity.setCreatedDate(data.getCreatedDate());
        entity.setLastModifiedBy(data.getLastModifiedBy());
        entity.setLastModifiedDate(data.getLastModifiedDate());
        entity.setDeleted(data.getDeleted());
        entity.setVersion(data.getVersion());

        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(FilesEntity entity) {

        Files data = new Files();

        //元数据
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedDate(entity.getCreatedDate());
        data.setLastModifiedBy(entity.getLastModifiedBy());
        data.setLastModifiedDate(entity.getLastModifiedDate());
        data.setVersion(entity.getVersion());
        data.setDeleted(entity.getDeleted());
        data.setId(entity.getId());

        //todo 业务字段

        jdbcFilesRepository.save(data);
    }
}

