package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.common.dict.DictQueryService;
import com.feiniaojin.ddd.hcms.domain.vo.DictVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Resource
    private DictQueryService dictQueryService;

    @GetMapping("/dataTypeDict")
    public List<DictVo> getDataTypeDict() {
        return dictQueryService.getDataTypeDict();
    }
}
