package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.entry.EntryCommandService;
import com.feiniaojin.application.service.content.entry.EntryQueryService;
import com.feiniaojin.application.service.content.entry.dto.EntryCreateCommand;
import com.feiniaojin.application.service.content.entry.dto.EntryQuery;
import com.feiniaojin.application.service.content.entry.dto.EntryView;
import com.feiniaojin.ui.web.annotation.UrlRequestParam;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content-entries")
public class EntryController {

    @Resource
    private EntryCommandService commandService;

    @Resource
    private EntryQueryService queryService;

    @PutMapping("/createEntry")
    @ResponseBody
    public void addNewEntry(@RequestBody EntryCreateCommand command) {
        commandService.createEntry(command);
    }


    @GetMapping("/{entryId}")
    @ResponseBody
    public EntryView queryOne(@PathVariable String entryId) {
        EntryQuery entryQuery = new com.feiniaojin.application.service.content.entry.dto.EntryQuery();
        entryQuery.setEntryId(entryId);
        return queryService.findOne(entryQuery);
    }

    @GetMapping("/{typeId}/{filters}")
    public void filter(@PathVariable String typeId, @UrlRequestParam("filters") EntryQuery filters) {
        System.out.println(typeId);
        System.out.println(filters);
    }
}
