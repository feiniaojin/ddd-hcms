package com.feiniaojin.application.service.content.entry;

import com.feiniaojin.application.service.content.entry.dto.EntryQuery;
import com.feiniaojin.application.service.content.entry.dto.EntryView;
import com.feiniaojin.application.service.content.entryfield.EntryFieldQueryService;
import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldView;
import com.feiniaojin.ddd.hcms.domain.content.EntryEntity;
import com.feiniaojin.infrastructure.persistence.data.HcmsContentEntry;
import com.feiniaojin.infrastructure.persistence.jdbc.HcmsContentEntryRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EntryQueryService {

    @Resource
    private HcmsContentEntryRepository jdbcEntryRepository;

    @Resource
    private EntryFieldQueryService entryFieldQueryService;

    public Page<EntryView> pageList() {
        return null;
    }

    public EntryView findOne(EntryQuery query) {
        HcmsContentEntry entryData = jdbcEntryRepository.findOneByBizId(query.getEntryId());
        if(Objects.isNull(entryData)){
            throw new RuntimeException();
        }
        return this.translate(entryData);
    }

    private EntryView translate(HcmsContentEntry entryData) {
        EntryView entryView = new EntryView();

        entryView.setEntryId(entryData.getContentEntryId());
        entryView.setTypeId(entryData.getContentTypeId());

        List<EntryFieldView> entryFieldList = entryFieldQueryService.listByEntryId(entryData.getContentEntryId());
        entryView.setEntryFields(entryFieldList);
        return entryView;
    }
}
