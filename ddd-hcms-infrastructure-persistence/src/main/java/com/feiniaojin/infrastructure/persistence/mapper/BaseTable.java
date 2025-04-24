package com.feiniaojin.infrastructure.persistence.mapper;

import org.mybatis.dynamic.sql.AliasableSqlTable;

import java.util.function.Supplier;

/**
 *@Author anzhenjiang
 *@Description TODO
 */
public class BaseTable extends AliasableSqlTable<BaseTable> {
    protected BaseTable() {
        super("", BaseTable::new);
    }
}
