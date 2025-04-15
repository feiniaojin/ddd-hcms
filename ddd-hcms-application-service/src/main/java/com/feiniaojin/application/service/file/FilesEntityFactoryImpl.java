package com.feiniaojin.application.service.file;


import com.feiniaojin.ddd.hcms.domain.file.FilesEntity;
import com.feiniaojin.ddd.hcms.domain.file.FilesEntityFactory;

/**
 * 工厂存在的原因是解决复杂对象的创建问题，例如为对象的id属性赋值
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
public class FilesEntityFactoryImpl implements FilesEntityFactory {

    @Override
    public FilesEntity newInstance() {
        FilesEntity entity = new FilesEntity();
        //todo 完成创建逻辑
        return entity;
    }
}
