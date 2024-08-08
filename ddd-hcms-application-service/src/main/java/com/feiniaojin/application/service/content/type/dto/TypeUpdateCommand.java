package com.feiniaojin.application.service.content.type.dto;

import lombok.Data;

@Data
public class TypeUpdateCommand {
    private String typeId;
    private String displayName;
}
