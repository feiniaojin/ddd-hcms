package com.feiniaojin.application.service.content.entry.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
public class EntryQuery {

    String entryId;

    private List<Filter> filters;

    private Page page;

    private List<Sort> sorts;

    private List<Field> fields;

    public static EntryQuery parse(String paramValue) {
        if (StringUtils.isBlank(paramValue)) {
            return null;
        }
        EntryQuery filters = new EntryQuery();

        //todo 解析


        return filters;
    }

    @Data
    public static class Filter {
        private String fieldName;
        private String value;
        private String operator;

        public Filter(String fieldName, String value, String operator) {
            this.fieldName = fieldName;
            this.value = value;
            this.operator = operator;
        }
    }

    @Data
    public static class Sort {
        private String fieldName;
        private String order;

        public Sort(String fieldName, String order) {
            this.fieldName = fieldName;
            this.order = order;
        }
    }

    @Data
    public static class Field {
        private String order;
        private String fieldName;

        public Field(String order, String fieldName) {
            this.order = order;
            this.fieldName = fieldName;
        }
    }

    @Data
    public static class Page {
        private Integer curr;
        private Integer size;

        public Page(Integer curr, Integer size) {
            this.curr = curr;
            this.size = size;
        }
    }
}
