package com.feiniaojin.infrastructure.persistence.jdbc;

import com.feiniaojin.infrastructure.persistence.data.HcmsContentType;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 表名称：hcms_content_type自动生成的Repository
 * 表注释：Content Type表
 * NOTICE:本文件由代码生成器code-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
public interface HcmsContentTypeRepository extends CrudRepository<HcmsContentType, Long> {

    @Query("select * from hcms_content_type where content_type_id=:idValue")
    HcmsContentType findOne(String idValue);
}
