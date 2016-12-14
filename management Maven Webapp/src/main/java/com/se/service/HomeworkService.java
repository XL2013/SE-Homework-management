package com.se.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.se.pojo.Homework;

import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;

public interface HomeworkService {
		List<TeamHomework> getTeamHomeworks(String team_id);
		TeamHomework getTeamHomework(String homework_id,String team_id);
		
		
		Map<String,Object> getTeamHomeworkViewData();
		Map<String,Object> getTeamHomeworkViewData(String homework_name, String submit_time, String team_id);
		void setComment(String comment,String homework_id,String team_id);
		void addTeamHomework(String homework_id,String team_id);
		void updateTeamHomework(TeamHomework teamHomework);
		List<HomeworkFile> getTeamHomewokFiles(String homework_id,String team_id);
		void addHomeworkInfos(List<Homework> homeworks);
		void modifyHomeworkRatio(String courseID, double ratio);
		public List<Homework> getHomeworksInfoByCourseID(String courseID);
		Homework getHomework(String homework_id);	
		//小组作业文件部分
		void saveHomeworkFile(String team_id,String homework_id,MultipartFile[] files);
		boolean checkHomeworkFile(String file_name,String homework_id,String team_id);
		List<HomeworkFile> getHomeworkFiles(String team_id,String homework_id);
		void  submitTeamHomework(String team_id,String homework_id,String student_id);
		void  evaluateTeamHomework(String team_id,String homework_id,String correctInfo,int grade);

}
