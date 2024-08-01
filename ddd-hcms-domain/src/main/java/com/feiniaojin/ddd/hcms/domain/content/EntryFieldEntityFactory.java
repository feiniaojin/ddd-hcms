package com.feiniaojin.ddd.hcms.domain.content;

public interface EntryFieldEntityFactory {

    EntryFieldEntity newInstance(EntryId entryId,
                                 TypeFieldId typeFieldId,
                                 String fieldName,
                                 Integer fieldDataType,
                                 String fieldValue);
}
