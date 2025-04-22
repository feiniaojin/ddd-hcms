package com.feiniaojin.application.service.core;

import com.feiniaojin.application.service.core.dto.BaseQuery;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 *@Author anzhenjiang
 *@Description TODO
 */
@Service
public class BaseQueryService {
    public void find(String entry, BaseQuery query) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(entry).append(" where ");
        for (BaseQuery.Filter filter : query.getFilters()) {
            sql.append(" and ").append(filter.getFieldName());
            switch (filter.getOperator()) {
                case "$eq" -> sql.append(" = ").append(filter.getValue().get(0));
                case "$eqi" -> sql.append(" = lower(").append(filter.getValue().get(0)).append(")");
                case "$ne" -> sql.append(" != ").append(filter.getValue().get(0));
                case "$nei" -> sql.append(" != lower(").append(filter.getValue().get(0)).append(")");
                case "$lt" -> sql.append(" < ").append(filter.getValue().get(0));
                case "$lte" -> sql.append(" <= ").append(filter.getValue().get(0));
                case "$gt" -> sql.append(" > ").append(filter.getValue().get(0));
                case "$gte" -> sql.append(" >= ").append(filter.getValue().get(0));
                case "$in" -> sql.append(" in (").append(String.join(",", filter.getValue())).append(")");
                case "$notIn" -> sql.append(" not in (").append(String.join(",", filter.getValue())).append(")");
                case "$contains" -> sql.append(" like '%").append(filter.getValue().get(0)).append("%'");
                case "$notContains" -> sql.append(" not like '%").append(filter.getValue().get(0)).append("%'");
                case "$containsi" -> sql.append(" like lower('%").append(filter.getValue().get(0)).append("%')");
                case "$notContainsi" -> sql.append(" not like lower('%").append(filter.getValue().get(0)).append("%')");
                case "$null" -> sql.append(" is null");
                case "$notNull" -> sql.append(" is not null");
                case "$between" -> sql.append(" between ").append(filter.getValue().get(0)).append(" and ").append(filter.getValue().get(1));
                case "$startWith" -> sql.append(" like '").append(filter.getValue().get(0)).append("%'");
                case "$startWithi" -> sql.append(" like lower('").append(filter.getValue().get(0)).append("%')");
                case "$endWith" -> sql.append(" like '%").append(filter.getValue().get(0)).append("'");
                case "$endWithi" -> sql.append(" like lower('%").append(filter.getValue().get(0)).append("')");
            }
        }
        // 移除where条件中开头的and
        System.out.println(sql);
    }
}
