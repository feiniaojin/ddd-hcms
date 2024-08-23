package com.feiniaojin.infrastructure.persistence.data;

import jakarta.annotation.Generated;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：hcms_content_type_field
 * 表注释：Content Type表
 * NOTICE:本文件由代码生成器code-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
@Data
@Table("hcms_content_type_field")
@Generated("generator")
public class ContentTypeField implements Serializable {
    /**
     * 自增主键
     */
    @Id
    private Long id;
    /**
     * 业务唯一标识
     */
    private String fieldId;

    /**
     * 内容类型的唯一标识
     */
    private String typeId;
    /**
     * 属性名称
     */
    private String fieldName;
    /**
     * 属性的数据类型
     */
    private Integer fieldDataType;
    /**
     * 状态：0草稿，1已发布，2撤回
     */
    private Integer status;
    /**
     * 逻辑删除标记[0-正常；1-已删除]
     */
    private Integer deleted;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    @CreatedDate
    private Date createdTime;
    /**
     * 更新人
     */
    private String modifiedBy;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date modifiedTime;
    /**
     * 乐观锁
     */
    @Version
    private Long version;
}
