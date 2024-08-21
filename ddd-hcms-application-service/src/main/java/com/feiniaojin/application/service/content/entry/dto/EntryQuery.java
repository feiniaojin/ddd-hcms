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
        filters.setFilters(new java.util.ArrayList<>());
        filters.setPage(new Page(1, 10));
        filters.setSorts(new java.util.ArrayList<>());
        filters.setFields(new java.util.ArrayList<>());

        String[] params = paramValue.split("&");

        for (String param : params) {
            if (StringUtils.isBlank(param.trim())) {
                continue;
            }
            String[] kv = param.split("=");
            if (kv.length != 2) {
                continue;
            }
            String[] k = kv[0].split(":");
            if ("filters".equals(k[0])) {
                String[] v = kv[1].split(",");
                for (String vi : v) {
                    filters.getFilters().add(new Filter(k[1], vi, k[2]));
                }
            } else if ("page".equals(k[0])) {
                if ("size".equals(k[1])) {
                    filters.getPage().setSize(Integer.valueOf(kv[1]));
                } else if ("cur".equals(k[1])) {
                    filters.getPage().setCurr(Integer.valueOf(kv[1]));
                }
            } else if ("sorts".equals(k[0])) {
                filters.getSorts().add(new Sort(kv[1], Integer.valueOf(k[1]), k[2]));
            } else if ("fields".equals(k[0])) {
                filters.getFields().add(new Field(Integer.valueOf(k[1]), kv[1]));
            }

        }


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
        private Integer order;
        private String operator;

        public Sort(String fieldName, Integer order, String operator) {
            this.fieldName = fieldName;
            this.order = order;
            this.operator = operator;
        }
    }

    @Data
    public static class Field {
        private Integer order;
        private String fieldName;

        public Field(Integer order, String fieldName) {
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
