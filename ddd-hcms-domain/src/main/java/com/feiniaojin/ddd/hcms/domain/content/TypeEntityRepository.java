package com.feiniaojin.ddd.hcms.domain.content;

import com.feiniaojin.ddd.DomainRepository;

import java.util.List;

/**
 * ContentType仓储
 *
 * @author qinyujie
 */
public interface TypeEntityRepository extends DomainRepository<TypeEntity, TypeId> {

    List<TypeEntity> loadList();
}
