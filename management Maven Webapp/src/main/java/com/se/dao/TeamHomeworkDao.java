package com.se.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;

@Repository("teamHomeworkDao")
public interface TeamHomeworkDao {
	
	//小组作业部分
	@Select("select * from team_homework where team_id=#{team_id}")
	List<Homework> getTeamHomeWorks(String team_id);
	
	@Select("select * from team_homework where homework_id=#{homework_id}")
	TeamHomework getHomeworkByID(String homework_id);
	
	@Update("update team_homework set submit_time=#{submit_time},submitter=#{submitter}"
			+ ",status=#{status},grade=#{grade},correctInfo=#{correctInfo},student_comment=#{student_comment} "
			+ " where homework_id=#{homework_id} and team_id=#{team_id}")
	void updateHomework(TeamHomework teamHomework);
	
	@Insert("insert into team_homework(team_id,homework_id,submit_time,submitter,status,grade,correctInfo,student_comment) "
			+ " values(#{team_id},#{homework_id},#{submit_time},#{submitter},#{status},#{grade},#{correctInfo},#{student_comment})")
	void addHomework(TeamHomework teamHomework);
	
	//作业文件部分数据库代码
	@Insert("insert into homework_file(file_name,file_path,homework_id) values(#{file_name},#{file_path},#{homework_id})")
	void addHomeworkFile(HomeworkFile file );
	
	@Select("select * from homework_file where homework_id=#{homework_id}")
	List<HomeworkFile> getHomeworkFiles(String homework_id);
	
	@Select("select count(*) from homework_file where file_name=#{file_name} and file_path=#{file_path}")
	int isFileExist(@Param("file_name")String file_name,@Param("file_path")String file_path);
	
}
