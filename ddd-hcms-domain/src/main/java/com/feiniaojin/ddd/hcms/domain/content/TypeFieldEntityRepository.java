package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.DomainRepository;

import java.util.List;

/**
 * ContentField 仓储
 *
 * @author qinyujie
 */
public interface TypeFieldEntityRepository extends DomainRepository<TypeFieldEntity, TypeFieldId> {

    List<TypeFieldEntity> findByTypeId(String typeId);
}
