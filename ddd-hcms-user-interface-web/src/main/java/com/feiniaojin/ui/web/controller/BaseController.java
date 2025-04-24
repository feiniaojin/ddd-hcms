package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.core.BaseCommandService;
import com.feiniaojin.application.service.core.BaseQueryService;
import com.feiniaojin.application.service.core.dto.BaseQuery;
import com.feiniaojin.ui.web.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 *@Author anzhenjiang
 *@Description TODO
 */
@RestController
@RequestMapping("/")
public class BaseController {

    @Autowired
    private BaseQueryService baseQueryService;

    @Autowired
    private BaseCommandService baseCommandService;


    @RequestParam
    @GetMapping("/{entry}/find")
    public List<Map<String, Object>> find(@PathVariable String entry, BaseQuery query) throws UnsupportedEncodingException {
        return baseQueryService.find(entry, query);
    };

    @GetMapping("/{entry}/findOne/{id}")
    public Map<String, Object> findOne(@PathVariable String entry, @PathVariable String id){
        return baseQueryService.findOne(entry, id);
    };

    @PutMapping("/{entry}/create")
    public void create(){};

    @DeleteMapping("/{entry}/delete/{id}")
    public int delete(@PathVariable String entry, @PathVariable String id){
        return baseCommandService.delete(entry, id);
    };

    @PostMapping("/{entry}/update/{id}")
    public void update(){};
}
