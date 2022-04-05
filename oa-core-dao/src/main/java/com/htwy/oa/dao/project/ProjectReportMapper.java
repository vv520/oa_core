package com.htwy.oa.dao.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * description
 *
 * @author Administrator 2022/02/27 14:59
 */
@Mapper
public interface ProjectReportMapper {
    List<Map<String, Object>> findPeojectReport(@Param("month") String month, @Param("page")int page, @Param("size")int size);
}
