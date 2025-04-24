package com.feiniaojin.infrastructure.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.mybatis3.*;

import java.util.List;

/**
 *@Author anzhenjiang
 *@Description TODO
 */

@Mapper
public interface BaseMapper extends CommonSelectMapper, CommonUpdateMapper, CommonInsertMapper {

}
