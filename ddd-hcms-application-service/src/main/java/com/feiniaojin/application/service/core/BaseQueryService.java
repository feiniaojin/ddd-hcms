package com.feiniaojin.application.service.core;

import com.feiniaojin.application.service.core.dto.BaseQuery;
import com.feiniaojin.infrastructure.persistence.data.ContentType;
import com.feiniaojin.infrastructure.persistence.data.ContentTypeField;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeFieldRepository;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeRepository;
import com.feiniaojin.infrastructure.persistence.mapper.ArticleTable;
import com.feiniaojin.infrastructure.persistence.mapper.BaseMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 *@Author anzhenjiang
 *@Description TODO
 */
@Service
public class BaseQueryService {

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Autowired
    private ContentTypeFieldRepository contentTypeFieldRepository;

    @Autowired
    private BaseMapper baseMapper;

    public List<Map<String, Object>> find(String entry, BaseQuery query) throws UnsupportedEncodingException {
        ContentType contentType = contentTypeRepository.getByDisplayName(entry);
        List<ContentTypeField> contentTypeFields = contentTypeFieldRepository.findByTypeId(contentType.getTypeId());
        SqlTable table = ArticleTable.of("hcms_article");
//        table.column()
        // V1.1
        List<SqlColumn<Object>> list = contentTypeFields.stream().map(item -> table.column(item.getFieldName())).toList();
        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder whereSql = SqlBuilder.select(list).from(table).where(table.column("deleted"), SqlBuilder.isEqualTo(0));

        for (BaseQuery.Filter filter : query.getFilters()) {
            switch (filter.getOperator()) {
                case "$eq" -> whereSql = whereSql.and(table.column(filter.getFieldName()), SqlBuilder.isEqualToWhenPresent(URLDecoder.decode(filter.getValue().get(0), "UTF-8")));
//                case "$eqi" -> sql.append(" = lower(").append(filter.getValue().get(0)).append(")");
//                case "$ne" -> sql.append(" != ").append(filter.getValue().get(0));
//                case "$nei" -> sql.append(" != lower(").append(filter.getValue().get(0)).append(")");
//                case "$lt" -> sql.append(" < ").append(filter.getValue().get(0));
//                case "$lte" -> sql.append(" <= ").append(filter.getValue().get(0));
//                case "$gt" -> sql.append(" > ").append(filter.getValue().get(0));
//                case "$gte" -> sql.append(" >= ").append(filter.getValue().get(0));
                case "$in" -> whereSql = whereSql.and(table.column(filter.getFieldName()), SqlBuilder.isInWhenPresent(filter.getValue()));
//                case "$notIn" -> sql.append(" not in (").append(String.join(",", filter.getValue())).append(")");
//                case "$contains" -> sql.append(" like '%").append(filter.getValue().get(0)).append("%'");
//                case "$notContains" -> sql.append(" not like '%").append(filter.getValue().get(0)).append("%'");
//                case "$containsi" -> sql.append(" like lower('%").append(filter.getValue().get(0)).append("%')");
//                case "$notContainsi" -> sql.append(" not like lower('%").append(filter.getValue().get(0)).append("%')");
//                case "$null" -> sql.append(" is null");
//                case "$notNull" -> sql.append(" is not null");
//                case "$between" -> sql.append(" between ").append(filter.getValue().get(0)).append(" and ").append(filter.getValue().get(1));
//                case "$startWith" -> sql.append(" like '").append(filter.getValue().get(0)).append("%'");
//                case "$startWithi" -> sql.append(" like lower('").append(filter.getValue().get(0)).append("%')");
//                case "$endWith" -> sql.append(" like '%").append(filter.getValue().get(0)).append("'");
//                case "$endWithi" -> sql.append(" like lower('%").append(filter.getValue().get(0)).append("')");
            }
        }
        SelectStatementProvider render = whereSql.build().render(RenderingStrategies.MYBATIS3);

        return baseMapper.selectManyMappedRows(render);

        // V1.0
//        StringBuilder sql = new StringBuilder();
//        sql.append("select * from ").append(entry).append(" where ");
//        for (BaseQuery.Filter filter : query.getFilters()) {
//            sql.append(" and ").append(filter.getFieldName());
//            switch (filter.getOperator()) {
//                case "$eq" -> sql.append(" = ").append(filter.getValue().get(0));
//                case "$eqi" -> sql.append(" = lower(").append(filter.getValue().get(0)).append(")");
//                case "$ne" -> sql.append(" != ").append(filter.getValue().get(0));
//                case "$nei" -> sql.append(" != lower(").append(filter.getValue().get(0)).append(")");
//                case "$lt" -> sql.append(" < ").append(filter.getValue().get(0));
//                case "$lte" -> sql.append(" <= ").append(filter.getValue().get(0));
//                case "$gt" -> sql.append(" > ").append(filter.getValue().get(0));
//                case "$gte" -> sql.append(" >= ").append(filter.getValue().get(0));
//                case "$in" -> sql.append(" in (").append(String.join(",", filter.getValue())).append(")");
//                case "$notIn" -> sql.append(" not in (").append(String.join(",", filter.getValue())).append(")");
//                case "$contains" -> sql.append(" like '%").append(filter.getValue().get(0)).append("%'");
//                case "$notContains" -> sql.append(" not like '%").append(filter.getValue().get(0)).append("%'");
//                case "$containsi" -> sql.append(" like lower('%").append(filter.getValue().get(0)).append("%')");
//                case "$notContainsi" -> sql.append(" not like lower('%").append(filter.getValue().get(0)).append("%')");
//                case "$null" -> sql.append(" is null");
//                case "$notNull" -> sql.append(" is not null");
//                case "$between" -> sql.append(" between ").append(filter.getValue().get(0)).append(" and ").append(filter.getValue().get(1));
//                case "$startWith" -> sql.append(" like '").append(filter.getValue().get(0)).append("%'");
//                case "$startWithi" -> sql.append(" like lower('").append(filter.getValue().get(0)).append("%')");
//                case "$endWith" -> sql.append(" like '%").append(filter.getValue().get(0)).append("'");
//                case "$endWithi" -> sql.append(" like lower('%").append(filter.getValue().get(0)).append("')");
//            }
//        }
//        // 移除where条件中开头的and
//        System.out.println(sql);
    }
}
