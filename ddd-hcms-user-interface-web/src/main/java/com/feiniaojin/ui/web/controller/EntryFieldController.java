package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.entryfield.EntryFieldCommandService;
import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldCreateCommand;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content-entry-fields")
public class EntryFieldController {

    @Resource
    private EntryFieldCommandService commandService;

    @PostMapping
    public void create(@RequestBody EntryFieldCreateCommand command) {
        commandService.createEntryField(command);
    }
}
