package com.feiniaojin.application.service.content.entryfield.dto;

import lombok.Data;

@Data
public class EntryFieldCreateCommand {
    private String entryEntityId;
    private String fieldEntityId;
    private String fieldName;
    private Integer fieldDataType;
    private String fieldValue;
}
