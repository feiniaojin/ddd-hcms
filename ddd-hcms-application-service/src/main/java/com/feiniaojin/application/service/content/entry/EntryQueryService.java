package com.feiniaojin.application.service.content.entry;

import com.feiniaojin.application.service.content.entry.dto.EntryQuery;
import com.feiniaojin.application.service.content.entry.dto.EntryView;
import com.feiniaojin.application.service.content.entryfield.EntryFieldQueryService;
import com.feiniaojin.application.service.content.entryfield.dto.EntryFieldView;
import com.feiniaojin.infrastructure.persistence.data.HcmsContentEntry;
import com.feiniaojin.infrastructure.persistence.jdbc.HcmsContentEntryRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Service
public class EntryQueryService {

    @Resource
    private HcmsContentEntryRepository jdbcEntryRepository;

    @Resource
    private EntryFieldQueryService entryFieldQueryService;

    public Page<EntryView> q(EntryQuery query) {


        return null;
    }

    public EntryView findOne(EntryQuery query) {
        HcmsContentEntry entryData = jdbcEntryRepository.findOneByBizId(query.getEntryId());
        if (Objects.isNull(entryData)) {
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

    public static void main(String[] args) {
        EntryQuery query = new EntryQuery();
        EntryQuery.Filter filter1 = new EntryQuery.Filter("userName", "feiniaojin", "$eq");
        EntryQuery.Filter filter2 = new EntryQuery.Filter("count", "100", "$eq");
        query.setFilters(List.of(filter1, filter2));

        EntryQuery.Sort sort1 = new EntryQuery.Sort("userName", "asc");
        EntryQuery.Sort sort2 = new EntryQuery.Sort("count", "desc");
        query.setSorts(List.of(sort1, sort2));

        EntryQuery.Page page1 = new EntryQuery.Page(2, 10);
        query.setPage(page1);

        List<EntryQuery.Filter> filters = query.getFilters();
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("select t0.entry_id from hcms_content_entry_field as t0 ");

        //拼接join语句
        for (int i = 1; i < filters.size(); i++) {
            sqlBuilder.append("inner join hcms_content_entry_field as t").append(i)
                    .append(" on t0.entry_id = t").append(i).append(".entry_id");
        }

        //拼接where
        sqlBuilder.append(" where ");
        for (int i = 0; i < filters.size(); i++) {
            sqlBuilder.append("t").append(i).append(".field_name=")
                    .append(filters.get(i).getFieldName())
                    .append(" and t").append(i).append(".field_value=")
                    .append(filters.get(i).getValue());
            if (i < filters.size() - 1) {
                sqlBuilder.append(" and ");
            }
        }

        //拼接排序
        List<EntryQuery.Sort> sorts = query.getSorts();
        if (!CollectionUtils.isEmpty(sorts)) {
            sqlBuilder.append(" order by ");
            for (int i = 0; i < sorts.size(); i++) {
                EntryQuery.Sort sort = sorts.get(i);
                //todo 需要记录每个排序字段出现在哪个连接表中，即t1或者t几等，否则无法排序
                sqlBuilder.append(sort.getFieldName()).append(" ").append(sort.getOrder());
                if (i < sorts.size() - 1) {
                    sqlBuilder.append(",");
                }
            }
        }

        //拼接分页
        EntryQuery.Page page = query.getPage();
        if (Objects.nonNull(page)) {
            Integer curr = page.getCurr();
            Integer size = page.getSize();
            Integer limitStart = (curr - 1) * size;
            sqlBuilder.append(" limit ").append(limitStart).append(",").append(size);
        }

        String sql = sqlBuilder.toString();
    }
}
