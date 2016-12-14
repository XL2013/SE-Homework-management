package com.se.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.Team;

@Repository("teamDao")
public interface TeamDao {
	//小组table部分
	@Select("select * from team_table where team_id=#{team_id}")
	Team getTeamInfo(String team_id);
	
    @Insert("insert into team_table(team_id,course_id,leader_id,email) values(#{team_id},#{course_id},#{leader_id},#{email})")
    void addTeam(Team team);
    
    @Select("select * from team_table where course_id=#{course_id}")
    List<Team> getCourseTeams(String course_id);
      
    @Update("update team_table set email=#{email} where team_id=#{team_id} ")
    void setEmail(@Param("team_id")String team_id,@Param("email")String email);
    
    @Update("update team_table set leader_id=#{leader_id} where team_id=#{team_id}")
    void setTeamLeader(@Param("team_id")String team_id,@Param("leader_id")String leader_id);
    
    
    // table stu_team 操作部分,
    //todo:新建相关dao类把这部分代码加入
    @Insert("insert into stu_team(team_id,student_id,ratio) values(#{team_id},#{student_id},#{ratio})")
    void addTeamMember(@Param("team_id")String team_id,@Param("student_id")String student_id,@Param("ratio")double ratio);
    
    @Select("select ratio from stu_team where team_id=#{team_id} and student_id=#{student_id}")
    Double getMemberRatio(@Param("team_id")String team_id,@Param("student_id")String student_id);
    
    @Update("update stu_team set ratio=#{ratio} where team_id=#{team_id} and student_id=#{student_id}")
    void setMemberRatio(@Param("team_id")String team_id,@Param("student_id")String student_id,@Param("ratio")double ratio);
    
    @Select("select student_id from stu_team where team_id=#{team_id}")
    List<String> getStudentsByTID(String team_id);
	@Select("select team_id from stu_team where student_id=#{student_id}")
	String getTeamId(String student_id);
    @Select("select stu_team.team_id from stu_team , team_table where stu_team.team_id=team_table.team_id and"
    		+ " student_id=#{student_id} and course_id=#{course_id}")
    String searchTeamBySC(@Param("student_id")String student_id,@Param("course_id")String course_id);
    
   
}
