package com.feiniaojin.ddd.hcms.domain.file;

import com.feiniaojin.ddd.AbstractDomainMask;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.util.Date;

/**
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
@Data
@Generated("generator")
public class FilesEntity extends AbstractDomainMask {
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
}
