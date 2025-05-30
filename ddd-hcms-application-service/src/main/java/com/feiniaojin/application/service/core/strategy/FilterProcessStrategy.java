package com.feiniaojin.application.service.core.strategy;

import com.feiniaojin.application.service.core.dto.BaseQuery;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;

public interface FilterProcessStrategy {

    QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder process(QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder, BaseQuery.Filter filter);
}
