package com.se.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.se.pojo.TeamConfig;

@Repository("teamConfigDao")
public interface TeamConfigDao {
		
	@Insert("insert into team_config(course_id,config_id,team_min,team_max,prefix) values(#{course_id},#{config_id},#{team_min},#{team_max},#{prefix})")
	void addTeamConfig(TeamConfig teamConfig);
	
	@Delete("delete from team_config where config_id=#{config_id}")
	void deleteTeamConfig(String config_id);
	
	@Select("select * from team_config where course_id=#{course_id}")
	TeamConfig getTeamConfig(String course_id);
}
