package com.se.service;

import java.util.List;

import com.se.pojo.Student;
import com.se.pojo.Team;
import com.se.pojo.TeamConfig;

public interface TeamService {
	void addTeamConfig(String course_id,String year,String class_id,String max,String min);
	TeamConfig getTeamConfig(String course_id);
	
	Team getStudentTeam(String course_id,String student_id);
	List<Student> getTeamStudents(String team_id);
	
	Team addTeam(String course_id,String student_id);
	
	Team getTeamById(String team_id);
	
	void addTeamMember(String team_id,String student_id);

}
