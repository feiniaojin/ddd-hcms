package com.feiniaojin.infrastructure.persistence.mapper;

import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlTable;

import java.sql.JDBCType;


/**
 *@Author anzhenjiang
 *@Description TODO
 */
public class ArticleTable extends AliasableSqlTable<ArticleTable> {

    public ArticleTable() {
        super("hcms_article", ArticleTable::new);
    }

}
