package com.feiniaojin.ddd.hcms.domain.content;

/**
 * @author qinyujie
 */
public interface TypeEntityFactory {

    TypeEntity newInstance(String displayName);
}
