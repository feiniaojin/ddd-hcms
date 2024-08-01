package com.feiniaojin.ddd.hcms.domain.content;

/**
 * @author qinyujie
 */
public interface EntryEntityFactory {
    EntryEntity newInstance(TypeId typeId);
}
