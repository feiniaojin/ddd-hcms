package com.feiniaojin.application.service.common.dict;

import com.feiniaojin.ddd.hcms.domain.enums.DataTypeEnum;
import com.feiniaojin.ddd.hcms.domain.vo.DictVo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DictQueryService {

    public List<DictVo> getDataTypeDict() {
        return Arrays.stream(DataTypeEnum.values()).map(dataTypeEnum -> {
            DictVo dictVo = new DictVo();
            dictVo.setCode(dataTypeEnum.getDataType());
            dictVo.setValue(dataTypeEnum.getName());
            return dictVo;
        }).collect(Collectors.toList());
    }
}
