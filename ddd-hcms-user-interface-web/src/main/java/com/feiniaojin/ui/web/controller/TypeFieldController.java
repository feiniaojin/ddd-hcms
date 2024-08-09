package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.typefield.TypeFieldCommandService;
import com.feiniaojin.application.service.content.typefield.TypeFieldQueryService;
import com.feiniaojin.application.service.content.typefield.dto.TypeFieldCreateCommand;
import com.feiniaojin.application.service.content.typefield.dto.TypeFieldUpdateCommand;
import com.feiniaojin.application.service.content.typefield.dto.TypeFiledView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/content-type-fields")
public class TypeFieldController {

    @Resource
    private TypeFieldCommandService commandService;

    @Resource
    private TypeFieldQueryService queryService;

    @PutMapping("/create")
    @ResponseBody
    public void addNewTypeField(@RequestBody TypeFieldCreateCommand command) {
        commandService.createTypeField(command);
    }

    @PostMapping("/update")
    @ResponseBody
    public void updateTypeField(@RequestBody TypeFieldUpdateCommand command) {
        commandService.updateTypeField(command);
    }

    @GetMapping("/{fieldId}")
    @ResponseBody
    public TypeFiledView queryOne(@PathVariable String fieldId) {
        return queryService.findOne(fieldId);
    }

    @DeleteMapping("/{fieldId}")
    @ResponseBody
    public void delete(@PathVariable String fieldId) {
        commandService.delete(fieldId);
    }

    @GetMapping("/list/{typeId}")
    @ResponseBody
    public List<TypeFiledView> queryList(@PathVariable String typeId) {
        return queryService.findList(typeId);
    }

    @ResponseBody
    @PostMapping("/release/{fieldId}")
    public void release(@PathVariable String fieldId) {
        commandService.release(fieldId);
    }

    @ResponseBody
    @PostMapping("/unRelease/{fieldId}")
    public void unRelease(@PathVariable String fieldId) {
        commandService.unRelease(fieldId);
    }




}
