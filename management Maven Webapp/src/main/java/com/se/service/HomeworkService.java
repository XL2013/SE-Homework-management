package com.se.service;

import java.util.List;

import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;

public interface HomeworkService {
		List<TeamHomework> getTeamHomeworks(String team_id);
		TeamHomework getTeamHomework(String homework_id,String team_id);
		void setComment(String comment,String homework_id,String team_id);
		void addTeamHomework(String homework_id,String team_id);
		void updateTeamHomework(TeamHomework teamHomework);
		List<HomeworkFile> getTeamHomewokFiles(String homework_id,String team_id);
}
