package com.feiniaojin.infrastructure.persistence.jdbc;

import com.feiniaojin.infrastructure.persistence.data.ContentEntry;
import org.springframework.data.repository.CrudRepository;

/**
 * 表名称：hcms_content_entry自动生成的Repository
 * 表注释：内容实例表
 * NOTICE:本文件由代码生成器code-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
public interface ContentEntryRepository extends CrudRepository<ContentEntry, Long> {

    ContentEntry getByEntryId(String contentEntryId);

}
