package com.feiniaojin.infrastructure.persistence.jdbc;

import com.feiniaojin.infrastructure.persistence.data.ContentEntryField;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 表名称：hcms_content_entry_field自动生成的Repository
 * 表注释：内容实例表
 * NOTICE:本文件由代码生成器code-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
public interface ContentEntryFieldRepository extends CrudRepository<ContentEntryField, Long> {

    @Query("select * from hcms_content_entry_field where entry_field_id=:idValue")
    ContentEntryField findOneByBizId(String idValue);

    @Query("select * from hcms_content_entry_field where entry_id=:entryId")
    List<ContentEntryField> listByEntryId(String entryId);
}
