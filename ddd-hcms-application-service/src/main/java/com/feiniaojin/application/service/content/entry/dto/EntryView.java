package com.feiniaojin.application.service.content.entry.dto;

import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldView;
import lombok.Data;
import java.util.List;

@Data
public class EntryView {
    private String entryId;
    private String typeId;
    private List<EntryFieldView> entryFields;
}
