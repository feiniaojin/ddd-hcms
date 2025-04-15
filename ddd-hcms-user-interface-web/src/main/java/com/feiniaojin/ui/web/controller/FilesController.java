package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.file.FilesEntityCommandService;
import com.feiniaojin.application.service.file.FilesEntityQueryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Files类Controller类
 * 表名称：hcms_files
 * 表注释：文件表
 * NOTICE:本文件由代码生成器ddd-generator生成
 * github：https://github.com/feiniaojin/ddd-generator
 */
@RestController
@RequestMapping("files")
public class FilesController {
    @Resource
    private FilesEntityCommandService entityCommandService;

    @Resource
    private FilesEntityQueryService entityQueryService;
}
