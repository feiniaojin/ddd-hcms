package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
import com.feiniaojin.ddd.hcms.domain.vo.PageVo;
import com.feiniaojin.infrastructure.persistence.data.ContentType;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TypeEntityRepositoryImpl implements TypeEntityRepository {

    @Resource
    private ContentTypeRepository jdbcRepository;

    @Override
    public TypeEntity load(TypeId entityId) {

        String idValue = entityId.getValue();

        ContentType hcmsContentType = jdbcRepository.findOne(idValue);

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
        ContentType data = new ContentType();

        data.setId(entity.getId());
        data.setVersion(entity.getVersion());
        data.setCreatedBy(entity.getCreatedBy());
        data.setCreatedTime(entity.getCreatedDate());
        data.setModifiedBy(entity.getLastModifiedBy());
        data.setModifiedTime(entity.getLastModifiedDate());
        data.setDeleted(entity.getDeleted());

        data.setStatus(entity.getStatus());
        data.setDisplayName(entity.getDisplayName());
        data.setTypeId(entity.getTypeId().getValue());

        jdbcRepository.save(data);
    }

    @Override
    public List<TypeEntity> loadList() {
        List<ContentType> hcmsContentTypeList = jdbcRepository.findAllList();
        return translate(hcmsContentTypeList);
    }

    @Override
    public PageVo<TypeEntity> findByPage(Integer pageIndex, Integer pageSize) {
        List<ContentType> list = jdbcRepository.findByPage(pageIndex - 1, pageSize);
        long total = jdbcRepository.findByPageCount();

        PageVo<TypeEntity> pageVo = new PageVo<>();
        pageVo.setTotal(total);
        pageVo.setData(translate(list));
        return pageVo;
    }


    private List<TypeEntity> translate(List<ContentType> hcmsContentTypeList) {
        List<TypeEntity> typeEntityList = new ArrayList<>();
        for (ContentType hcmsContentType:hcmsContentTypeList) {
            TypeEntity typeEntity = new TypeEntity();
            typeEntity.setTypeId(new TypeId(String.valueOf(hcmsContentType.getId())));
            typeEntity.setDisplayName(hcmsContentType.getDisplayName());
            typeEntity.setStatus(hcmsContentType.getStatus());

            //version id
            typeEntity.setId(hcmsContentType.getId());
            typeEntity.setVersion(hcmsContentType.getVersion());
            typeEntity.setDeleted(hcmsContentType.getDeleted());
            typeEntityList.add(typeEntity);
        }
        return typeEntityList;
    }
}
