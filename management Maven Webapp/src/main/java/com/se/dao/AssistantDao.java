package com.se.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.Assistant;

@Repository("assistantDao")
public interface AssistantDao {
	
	@Select("select * from assistant_table where teacher_id=#{teacher_id}")
	List<Assistant> getAssistantByTID(String teacher_id);
	
	@Select("select * from assistant_table where assistant_id=#{assistant_id}")
	Assistant getAssistant(String assistant_id);
	
	@Select("select assistant_id from assistant_team where team_id=#{team_id}")
	String getTeamAssistant(String team_id);
	
	@Insert("insert into assistant_team(team_id,assistant_id) values(#{team_id},#{assistant_id})")
	void addTeamAssistant(@Param("team_id")String team_id,@Param("assistant_id")String assistant_id);
	
	@Update("update assistant_team set assistant_id=#{assistant_id} where team_id=#{team_id}")
	void setTeamAssistant(@Param("team_id")String team_id,@Param("assistant_id")String assistant_id);
}
