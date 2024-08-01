package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.typefield.TypeFieldCommandService;
import com.feiniaojin.application.service.content.typefield.dto.TypeFieldCreateCommand;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content-type-fields")
public class TypeFieldController {

    @Resource
    private TypeFieldCommandService commandService;

    @PostMapping
    @ResponseBody
    public void addNewTypeField(@RequestBody TypeFieldCreateCommand command) {
        commandService.createTypeField(command);
    }

}
