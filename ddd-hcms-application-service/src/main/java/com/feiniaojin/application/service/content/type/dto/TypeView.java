package com.feiniaojin.application.service.content.type.dto;


import com.feiniaojin.application.service.content.typefield.dto.TypeFiledView;
import lombok.Data;

import java.util.List;

@Data
public class TypeView {
    private String typeEntityId;
    private String displayName;
    private List<TypeFiledView> typeFiledList;
}
