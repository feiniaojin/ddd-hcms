package com.feiniaojin.application.service.content.entry;

import com.feiniaojin.application.service.content.entry.dto.EntryQuery;
import com.feiniaojin.application.service.content.entry.dto.EntryView;
import com.feiniaojin.application.service.content.entryfield.EntryFieldQueryService;
import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldView;
import com.feiniaojin.gracefulresponse.data.PageBean;
import com.feiniaojin.infrastructure.persistence.data.ContentEntry;
import com.feiniaojin.infrastructure.persistence.data.ContentEntryField;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentEntryFieldRepository;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentEntryRepository;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EntryQueryService {

    @Resource
    private ContentEntryRepository jdbcEntryRepository;

    @Resource
    private ContentEntryFieldRepository jdbcEntryFieldRepository;

    @Resource
    private EntryFieldQueryService entryFieldQueryService;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public PageBean<EntryView> q(EntryQuery query) {

        final ParseResult parseResult = parseQuery(query);

        String preSql = parseResult.getPreSql();

        List<String> entryIds = jdbcTemplate.execute(preSql, (PreparedStatementCallback<List<String>>) ps -> {
            List<Object> paramList = parseResult.getParamList();
            for (int i = 0; i < paramList.size(); i++) {
                ps.setObject(i + 1, paramList.get(i));
            }
            ResultSet resultSet = ps.executeQuery();
            List<String> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(resultSet.getString("entry_id"));
            }
            return result;
        });

        //根据id加载数据
        List<ContentEntryField> entryFields = jdbcEntryFieldRepository.queryList(entryIds);
        return populate(entryIds, entryFields);
    }

    private PageBean<EntryView> populate(List<String> entryIds, List<ContentEntryField> entryFields) {
        PageBean<EntryView> pageBean = new PageBean<>();
        List<EntryView> entryViews = new ArrayList<>();
        Map<String, List<ContentEntryField>> map = entryFields.stream().collect(Collectors.groupingBy(ContentEntryField::getEntryId));
        for (String entryId : entryIds) {
            EntryView entryView = new EntryView();
            entryView.setEntryId(entryId);
            List<ContentEntryField> fields = map.get(entryId);
            if (!CollectionUtils.isEmpty(fields)) {
                List<EntryFieldView> fieldViews = converter(fields);
                entryView.setEntryFields(fieldViews);
            }
            entryViews.add(entryView);
        }
        pageBean.setList(entryViews);
        return pageBean;
    }

    private List<EntryFieldView> converter(List<ContentEntryField> fields) {

        return fields.stream().map(e -> {
            EntryFieldView fieldView = new EntryFieldView();
            fieldView.setFieldId(e.getEntryFieldId());
            fieldView.setFieldName(e.getFieldName());
            fieldView.setFieldValue(e.getFieldValue());
            return fieldView;
        }).collect(Collectors.toList());
    }

    /**
     * 解析Query对象
     * @param query
     * @return
     */
    private static ParseResult parseQuery(EntryQuery query) {

        ParseResult parseResult = new ParseResult();

        String typeId = query.getTypeId();

        List<EntryQuery.Filter> filters = query.getFilters();

        List<Object> params = new ArrayList<>();
        Map<String, Integer> tableNameMap = new HashMap<>();
        //关联的表从1开始，0表恒为hcms_content_entry
        int tableNumber = 1;
        //filter
        for (EntryQuery.Filter filter : filters) {
            tableNameMap.put(filter.getFieldName(), tableNumber);
            tableNumber++;
        }

        List<EntryQuery.Sort> sorts = query.getSorts();
        for (EntryQuery.Sort sort : sorts) {
            //已包含该字段，跳过
            if (tableNameMap.containsKey(sort.getFieldName())) {
                continue;
            }
            tableNameMap.put(sort.getFieldName(), tableNumber);
            tableNumber++;
        }

        List<EntryQuery.Field> fields = query.getFields();
        if (!CollectionUtils.isEmpty(fields)) {
            for (EntryQuery.Field field : fields) {
                if (tableNameMap.containsKey(field.getFieldName())) {
                    continue;
                }
                tableNameMap.putIfAbsent(field.getFieldName(), tableNumber);
                tableNumber++;
            }
        }

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select t0.entry_id from hcms_content_entry as t0 ");

        //拼接join语句
        for (EntryQuery.Filter filter : filters) {
            Integer tn = tableNameMap.get(filter.getFieldName());
            sqlBuilder.append(" inner join hcms_content_entry_field as t").append(tn)
                    .append(" on t0.entry_id = t").append(tn).append(".entry_id");
        }

        //拼接where，只能查询对应typeId的数据
        sqlBuilder.append(" where t0.deleted=0 and t0.type_id=")
                .append(typeId).append(" ");

        for (EntryQuery.Filter filter : filters) {
            Integer tn = tableNameMap.get(filter.getFieldName());
            sqlBuilder.append(" and t").append(tn).append(".field_name=?");
            params.add(filter.getFieldName());
            sqlBuilder.append(" and t").append(tn).append(".field_value=?");
            params.add(filter.getValue());
        }

        //拼接排序
        //判断当前排序的字段是否在where参数列表里
        List<EntryQuery.Sort> querySorts = query.getSorts();
        if (!CollectionUtils.isEmpty(querySorts)) {
            sqlBuilder.append(" order by ");

            for (EntryQuery.Sort sort : querySorts) {
                Integer tn = tableNameMap.get(sort.getFieldName());
                sqlBuilder.append("t").append(tn).append(".")
                        .append("field_value").append(" ").append(sort.getOperator()).append(",");
            }
            if (sqlBuilder.charAt(sqlBuilder.length() - 1) == ',') {
                sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
            }
        }

        //拼接分页
        EntryQuery.Page page = query.getPage();
        if (Objects.nonNull(page)) {
            Integer curr = page.getCurr();
            Integer size = page.getSize();
            Integer limitStart = (curr - 1) * size;
            sqlBuilder.append(" limit ").append(limitStart).append(",").append(size);
        }

        String sql = sqlBuilder.toString();

        parseResult.setPreSql(sql);
        parseResult.setParamList(params);
        return parseResult;
    }

    public EntryView findOne(EntryQuery query) {
        ContentEntry entryData = jdbcEntryRepository.findOneByBizId(query.getEntryId());
        if (Objects.isNull(entryData)) {
            throw new RuntimeException();
        }
        return this.translate(entryData);
    }

    private EntryView translate(ContentEntry entryData) {
        EntryView entryView = new EntryView();

        entryView.setEntryId(entryData.getEntryId());
        entryView.setTypeId(entryData.getTypeId());

        List<EntryFieldView> entryFieldList = entryFieldQueryService.listByEntryId(entryData.getEntryId());
        entryView.setEntryFields(entryFieldList);
        return entryView;
    }

    public static void main(String[] args) {

        String typeId = "product";

        EntryQuery query = new EntryQuery();

        query.setTypeId(typeId);
        EntryQuery.Filter filter1 = new EntryQuery.Filter("userName", "feiniaojin", "$eq");
        EntryQuery.Filter filter2 = new EntryQuery.Filter("count", "100", "$eq");
        query.setFilters(List.of(filter1, filter2));

        EntryQuery.Sort sort1 = new EntryQuery.Sort("userName", 0, "asc");
        EntryQuery.Sort sort2 = new EntryQuery.Sort("count", 1, "desc");
        query.setSorts(List.of(sort1, sort2));

        EntryQuery.Page page1 = new EntryQuery.Page(1, 10);
        query.setPage(page1);

        ParseResult parseResult = parseQuery(query);

        System.out.println(parseResult);
    }

    /**
     * Query解析结果
     */
    @Data
    private static class ParseResult {
        private String preSql;
        private List<Object> paramList;
    }
}
