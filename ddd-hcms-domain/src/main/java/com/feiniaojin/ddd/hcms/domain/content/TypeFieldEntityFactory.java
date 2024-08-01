package com.feiniaojin.ddd.hcms.domain.content;

/**
 * @author qinyujie
 */
public interface TypeFieldEntityFactory {

    TypeFieldEntity newInstance(TypeId typeId, String fieldName, Integer fieldDataType);
}
