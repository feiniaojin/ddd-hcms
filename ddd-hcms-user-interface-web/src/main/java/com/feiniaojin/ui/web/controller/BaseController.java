package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.core.BaseCommandService;
import com.feiniaojin.application.service.core.BaseQueryService;
import com.feiniaojin.application.service.core.dto.BaseQuery;
import com.feiniaojin.ui.web.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
    @GetMapping("/{resources}/find")
    public List<Map<String, Object>> find(@PathVariable String resources, BaseQuery query) throws UnsupportedEncodingException {
        return baseQueryService.find(resources, query);
    }

    @GetMapping("/{resources}/findOne/{id}")
    public Map<String, Object> findOne(@PathVariable String resources, @PathVariable String id){
        return baseQueryService.findOne(resources, id);
    }

    @PostMapping("/{resources}/create")
    public void create(@PathVariable String resources, @RequestBody HashMap<String, Object> paramsMap){
        baseCommandService.create(resources, paramsMap);
    }

    @DeleteMapping("/{resources}/delete/{id}")
    public int delete(@PathVariable String resources, @PathVariable String id){
        return baseCommandService.delete(resources, id);
    }

    @PutMapping("/{resources}/update/{id}")
    public int update(@PathVariable String resources, @PathVariable String id, @RequestBody HashMap<String, Object> paramsMap){
        return baseCommandService.update(resources, id, paramsMap);
    }
}
