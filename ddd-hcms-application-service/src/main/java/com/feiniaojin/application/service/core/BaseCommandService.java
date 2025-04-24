package com.feiniaojin.application.service.core;

import com.feiniaojin.infrastructure.persistence.data.ContentType;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeFieldRepository;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentTypeRepository;
import com.feiniaojin.infrastructure.persistence.mapper.ArticleTable;
import com.feiniaojin.infrastructure.persistence.mapper.BaseMapper;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public int delete(String entry, String id) {
        ContentType contentType = contentTypeRepository.getByDisplayName(entry);
        SqlTable table = ArticleTable.of(contentType.getDisplayName());
        UpdateStatementProvider render = SqlBuilder.update(table).set(table.column("deleted")).equalTo(1).where(table.column("document_id"), SqlBuilder.isEqualTo(id))
                .build().render(RenderingStrategies.MYBATIS3);
        return baseMapper.update(render);
    }
}
