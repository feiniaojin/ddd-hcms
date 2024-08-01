package com.feiniaojin.application.service.content.entryfield;

import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldView;
import com.feiniaojin.infrastructure.persistence.data.ContentEntryField;
import com.feiniaojin.infrastructure.persistence.jdbc.ContentEntryFieldRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryFieldQueryService {

    @Resource
    private ContentEntryFieldRepository repository;

    public List<EntryFieldView> listByEntryId(String entryId) {
        List<ContentEntryField> list = repository.listByEntryId(entryId);
        return this.translates(list);
    }

    private List<EntryFieldView> translates(List<ContentEntryField> list) {
        return list.stream().map(this::translate).collect(Collectors.toList());
    }

    private EntryFieldView translate(ContentEntryField field) {
        EntryFieldView entryFieldView = new EntryFieldView();

        entryFieldView.setContentEntryId(field.getEntryId());
        entryFieldView.setEntryFieldId(field.getEntryFieldId());
        entryFieldView.setFieldId(field.getEntryFieldId());
        entryFieldView.setEntryFieldValue(field.getFieldValue());

        return entryFieldView;
    }
}
