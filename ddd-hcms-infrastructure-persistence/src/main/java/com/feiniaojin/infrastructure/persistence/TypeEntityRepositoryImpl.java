package com.feiniaojin.infrastructure.persistence;

import com.feiniaojin.ddd.hcms.domain.content.TypeEntity;
import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import com.feiniaojin.ddd.hcms.domain.content.TypeEntityRepository;
import com.feiniaojin.ddd.hcms.domain.vo.PageVo;
import com.feiniaojin.infrastructure.persistence.data.HcmsContentType;
import com.feiniaojin.infrastructure.persistence.jdbc.HcmsContentTypeRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<TypeEntity> loadList() {
        List<HcmsContentType> hcmsContentTypeList = jdbcRepository.findAllList();
        return translate(hcmsContentTypeList);
    }

    @Override
    public PageVo<TypeEntity> findByPage(Integer pageIndex, Integer pageSize) {
        List<HcmsContentType> list = jdbcRepository.findByPage(pageIndex - 1, pageSize);
        long total = jdbcRepository.findByPageCount();

        PageVo<TypeEntity> pageVo = new PageVo<>();
        pageVo.setTotal(total);
        pageVo.setData(translate(list));
        return pageVo;
    }


    private List<TypeEntity> translate(List<HcmsContentType> hcmsContentTypeList) {
        List<TypeEntity> typeEntityList = new ArrayList<>();
        for (HcmsContentType hcmsContentType:hcmsContentTypeList) {
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
