package com.example.demo.dao2;

import com.example.demo.entity.People;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PeopleMapper {
	List<People> selectAll(@Param("start_time") String start_time, @Param("end_time") String end_time);

	List<People> selectAllByDateRange(Map param);
}
