package com.feiniaojin.ddd.hcms.domain.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVo<T> {

    private Long total;

    private Integer pageIndex;

    private Integer pageSize;

    private List<T> data;
}
