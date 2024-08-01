package com.feiniaojin.application.service.content.typefield.dto;

import lombok.Data;

@Data
public class TypeFieldCreateCommand {

    private String typeId;

    private String fieldName;

    private Integer fieldDataType;

}
