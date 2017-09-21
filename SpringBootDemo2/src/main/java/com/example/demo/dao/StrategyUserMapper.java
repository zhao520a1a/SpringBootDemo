package com.example.demo.dao;

import com.example.demo.entity.StrategyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StrategyUserMapper {
	List<StrategyUser> selectAll(@Param("start_time") String start_time, @Param("end_time") String end_time);

	List<StrategyUser> selectAllByDateRange(Map param);
}
