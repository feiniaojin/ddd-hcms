package com.feiniaojin.application.service.core;

import com.feiniaojin.infrastructure.persistence.data.ContentType;
import com.feiniaojin.infrastructure.persistence.data.ContentTypeField;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeFieldRepository;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeRepository;
import com.feiniaojin.infrastructure.persistence.mapper.BaseMapper;
import com.feiniaojin.infrastructure.persistence.mapper.BaseTable;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.insert.GeneralInsertDSL;
import org.mybatis.dynamic.sql.insert.render.GeneralInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *@Author anzhenjiang
 *@Description TODO
 */
@Service
public class BaseCommandService {

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Autowired
    private ContentTypeFieldRepository contentTypeFieldRepository;

    @Autowired
    private BaseMapper baseMapper;

    public int delete(String resources, String id) {
        ContentType contentType = contentTypeRepository.getByDisplayName(resources);
        SqlTable table = BaseTable.of(contentType.getDisplayName());
        UpdateStatementProvider render = SqlBuilder.update(table).set(table.column("deleted")).equalTo(1).where(table.column("document_id"), SqlBuilder.isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);
        return baseMapper.update(render);
    }

    public int update(String resources, String id, HashMap<String, Object> paramsMap) {
        ContentType contentType = contentTypeRepository.getByDisplayName(resources);
        List<ContentTypeField> contentTypeFields = contentTypeFieldRepository.findByTypeId(contentType.getTypeId());
        SqlTable table = BaseTable.of(contentType.getDisplayName());
        List<String> fieldList = contentTypeFields.stream().map(ContentTypeField::getFieldName).toList();

        UpdateDSL<UpdateModel> update = SqlBuilder.update(table);

        for(Map.Entry<String, Object> entry : paramsMap.entrySet()){
            if(!fieldList.contains(entry.getKey())){
                continue;
            }
            update = update.set(table.column(entry.getKey())).equalTo(entry.getValue());
        }

        UpdateStatementProvider render = update.where(table.column("document_id"), SqlBuilder.isEqualTo(id)).build().render(RenderingStrategies.MYBATIS3);

        return baseMapper.update(render);
    }

    public void create(String resources, HashMap<String, Object> paramsMap) {
        ContentType contentType = contentTypeRepository.getByDisplayName(resources);
        List<ContentTypeField> contentTypeFields = contentTypeFieldRepository.findByTypeId(contentType.getTypeId());
        SqlTable table = BaseTable.of(contentType.getDisplayName());
        List<String> fieldList = contentTypeFields.stream().map(ContentTypeField::getFieldName).toList();
        GeneralInsertDSL documentId = SqlBuilder.insertInto(table).set(table.column("document_id")).toValue(UUID.randomUUID().toString())
                .set(table.column("created_by")).toValue("测试数据")
                .set(table.column("last_modified_by")).toValue("测试数据");
        for(Map.Entry<String, Object> entry : paramsMap.entrySet()){
            if(!fieldList.contains(entry.getKey())){
                continue;
            }
            documentId = documentId.set(table.column(entry.getKey())).toValue(entry.getValue());
        }
        GeneralInsertStatementProvider render = documentId.build().render(RenderingStrategies.MYBATIS3);
        baseMapper.generalInsert(render);
    }
}
