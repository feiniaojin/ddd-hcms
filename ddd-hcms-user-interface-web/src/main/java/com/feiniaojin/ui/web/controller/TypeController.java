package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.type.TypeCommandService;
import com.feiniaojin.application.service.content.type.TypeQueryService;
import com.feiniaojin.application.service.content.type.dto.TypeCreateCommand;
import com.feiniaojin.application.service.content.type.dto.TypeView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content-types")
public class TypeController {

    @Resource
    private TypeCommandService commandService;

    @Resource
    private TypeQueryService queryService;

    @ResponseBody
    @PostMapping
    public void create(@RequestBody TypeCreateCommand createCommand) {
        commandService.createTypeDraft(createCommand);
    }

    @GetMapping
    @ResponseBody
    public TypeView pageList(@PathVariable String id) {
        return queryService.findOne(id);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TypeView queryOne(@PathVariable String id) {
        return queryService.findOne(id);
    }
}
