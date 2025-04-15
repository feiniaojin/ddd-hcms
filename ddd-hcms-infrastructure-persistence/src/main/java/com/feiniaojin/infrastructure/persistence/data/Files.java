package com.feiniaojin.infrastructure.persistence.data;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.processing.Generated;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：hcms_files
 * 表注释：文件表
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
@Data
@Table("hcms_files")
@Generated("generator")
public class Files implements Serializable {
    /**
     * 自增主键
     */
    @Id
    private Long id;
    /**
     * 业务唯一标识
     */
    private String documentId;
    /**
     * 文件名称
     */
    private String name;
    /**
     * 备用文本
     */
    private String alternativeText;
    /**
     * 标题
     */
    private String title;
    /**
     * 扩展名
     */
    private String ext;
    /**
     * 文件夹路径
     */
    private String folderPath;
    /**
     * 文件路径
     */
    private String url;
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
    private Date createdDate;
    /**
     * 更新人
     */
    private String lastModifiedBy;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date lastModifiedDate;
    /**
     * 乐观锁
     */
    @Version
    private Long version;
}
