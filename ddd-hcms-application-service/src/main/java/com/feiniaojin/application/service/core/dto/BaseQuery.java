package com.feiniaojin.application.service.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *@Author anzhenjiang
 *@Description TODO
 */
@Data
public class BaseQuery {

    private static final Pattern FILTER_PATTERN =
            Pattern.compile("\\[(.*?)]");


    List<Filter> filters = new ArrayList<>();

    public BaseQuery (String paramValue) {
        if(StringUtils.isEmpty(paramValue)){
            return ;
        }
        Map<String, Filter> arrayFilters = new HashMap<>();
        String[] params = paramValue.split("&");
        for(String param : params){
            if(StringUtils.isBlank(param.trim())){
                continue;
            }
            String[] kv = param.split("=");
            Matcher matcher = FILTER_PATTERN.matcher(kv[0]);
            List<String> field = new ArrayList<>();
            while (matcher.find()) {
                field.add(matcher.group(1));  // 如 name
            }
            if(field.isEmpty()){
                continue;
            }else if(field.size() == 2){
                // 无需处理数组情况
                String fieldName = field.get(0);
                String operator = field.get(1);
                Filter filter = new Filter(fieldName, Collections.singletonList(kv[1]), operator);
                filters.add(filter);
            }else if(field.size() == 3){
                String fieldName = field.get(0);
                String operator = field.get(1);
                if(arrayFilters.containsKey(fieldName)){
                    arrayFilters.get(fieldName).getValue().add(kv[1]);
                }else{
                    Filter filter = new Filter(fieldName, new ArrayList<>(), operator);
                    filter.getValue().add(kv[1]);
                    arrayFilters.put(fieldName, filter);
                    filters.add(filter);
                }
            }
        }
    }

    @Data
    @AllArgsConstructor
    public static class Filter{
        private String fieldName;

        private List<String> value;

        private String operator;
    }
}
