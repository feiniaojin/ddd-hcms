package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.type.TypeCommandService;
import com.feiniaojin.application.service.content.type.TypeQueryService;
import com.feiniaojin.application.service.content.type.dto.TypeCreateCommand;
import com.feiniaojin.application.service.content.type.dto.TypeUpdateCommand;
import com.feiniaojin.application.service.content.type.dto.TypeView;
import com.feiniaojin.ddd.hcms.domain.vo.PageQuery;
import com.feiniaojin.ddd.hcms.domain.vo.PageVo;
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

    @ResponseBody
    @PostMapping("/update")
    public void updateType(@RequestBody TypeUpdateCommand updateCommand) {
        commandService.updateTypeDraft(updateCommand);
    }

    @ResponseBody
    @PostMapping("/release/{typeId}")
    public void release(@PathVariable String typeId) {
        commandService.release(typeId);
    }

    @ResponseBody
    @PostMapping("/unRelease/{typeId}")
    public void unRelease(@PathVariable String typeId) {
        commandService.unRelease(typeId);
    }

    @PostMapping("/list")
    @ResponseBody
    public List<TypeView> getAllList() {
        return queryService.findList();
    }

    @GetMapping("/page")
    @ResponseBody
    public PageVo<TypeView> pageList(@RequestBody PageQuery pageQuery) {
        return queryService.findByPage(pageQuery);
    }


    @GetMapping("/{typeId}")
    @ResponseBody
    public TypeView queryOne(@PathVariable String typeId) {
        return queryService.findOne(typeId);
    }

    @DeleteMapping("/{typeId}")
    @ResponseBody
    public void delete(@PathVariable String typeId) {
        commandService.delete(typeId);
    }
}
