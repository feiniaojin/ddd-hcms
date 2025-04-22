package com.feiniaojin.infrastructure.persistence.jdbc;

import com.feiniaojin.infrastructure.persistence.data.ContentTypeField;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 表名称：hcms_content_type_field自动生成的Repository
 * 表注释：Content Type表
 * NOTICE:本文件由代码生成器code-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
public interface ContentTypeFieldRepository extends CrudRepository<ContentTypeField, Long> {

    @Query("select * from hcms_content_type_field where field_id=:fieldId and deleted=0 limit 1")
    ContentTypeField findOneByBizId(String fieldId);

    @Query("select * from hcms_content_type_field where type_id=:typeId and deleted=0")
    List<ContentTypeField> findByTypeId(String typeId);
}
