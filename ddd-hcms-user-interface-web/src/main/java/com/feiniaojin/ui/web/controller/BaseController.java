package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.core.BaseCommandService;
import com.feiniaojin.application.service.core.BaseQueryService;
import com.feiniaojin.application.service.core.dto.BaseCommand;
import com.feiniaojin.application.service.core.dto.BaseQuery;
import com.feiniaojin.ui.web.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
    public void find(@PathVariable String entry, BaseQuery query){
        baseQueryService.find(entry, query);
    };

    @GetMapping("/findOne")
    public void findOne(BaseQuery query){};

    @PostMapping("create")
    public void create(@RequestBody BaseCommand command){};

    @PostMapping("delete")
    public void delete(@RequestBody BaseCommand command){};

    @PostMapping("update")
    public void update(@RequestBody BaseCommand command){};
}
