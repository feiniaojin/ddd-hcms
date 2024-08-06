package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.type.TypeCommandService;
import com.feiniaojin.application.service.content.type.TypeQueryService;
import com.feiniaojin.application.service.content.type.dto.TypeCreateCommand;
import com.feiniaojin.application.service.content.type.dto.TypeView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/content-types")
public class TypeController {

    @Resource
    private TypeCommandService commandService;

    @Resource
    private TypeQueryService queryService;

    @ResponseBody
    @PutMapping("/create")
    public void addNewType(@RequestBody TypeCreateCommand createCommand) {
        commandService.createTypeDraft(createCommand);
    }

    @PostMapping("/list")
    @ResponseBody
    public List<TypeView> pageList() {
        return queryService.findList();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public TypeView queryOne(@PathVariable String id) {
        return queryService.findOne(id);
    }
}
