package com.feiniaojin.ui.web.controller;

import com.feiniaojin.application.service.content.entry.EntryCommandService;
import com.feiniaojin.application.service.content.entry.EntryQueryService;
import com.feiniaojin.application.service.content.entry.dto.EntryCreateCommand;
import com.feiniaojin.application.service.content.entry.dto.EntryQuery;
import com.feiniaojin.application.service.content.entry.dto.EntryView;
import com.feiniaojin.gracefulresponse.data.PageBean;
import com.feiniaojin.ui.web.annotation.UrlRequestParam;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entries")
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
        EntryQuery entryQuery = new EntryQuery();
        entryQuery.setEntryId(entryId);
        return queryService.findOne(entryQuery);
    }

    @GetMapping("/{typeId}/{query}")
    public PageBean<EntryView> q(@PathVariable String typeId, @UrlRequestParam("query") EntryQuery query) {
        query.setTypeId(typeId);
        return queryService.q(query);
    }
}
