package com.feiniaojin.application.service.content.typefield.dto;

import com.feiniaojin.ddd.hcms.domain.content.TypeId;
import lombok.Data;

@Data
public class TypeFiledView {

    private String fieldId;

    private String typeId;

    private String fieldName;

    private Integer fieldDataType;
}
