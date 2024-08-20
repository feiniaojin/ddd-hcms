package com.feiniaojin.application.service.test;

import com.feiniaojin.application.service.content.entry.EntryQueryService;
import com.feiniaojin.application.service.content.entry.dto.EntryQuery;

import java.util.List;

public class EntryQueryServiceTest {


    //todo 单元测试待完善，仅用于测试sql生成
    public void testQ() {
        EntryQueryService service = new EntryQueryService();
        EntryQuery entryQuery = new EntryQuery();
        EntryQuery.Filter filter1 = new EntryQuery.Filter("userName", "feiniaojin", "$eq");
        EntryQuery.Filter filter2 = new EntryQuery.Filter("count", "100", "$eq");
        entryQuery.setFilters(List.of(filter1, filter2));

        service.q(entryQuery);
    }
}
