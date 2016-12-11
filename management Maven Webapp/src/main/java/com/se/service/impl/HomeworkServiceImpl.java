package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.TeamHomeworkDao;
import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;
import com.se.service.HomeworkService;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{

	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Override
	public List<TeamHomework> getTeamHomeworks(String team_id) {
		
		return  teamHomeworkDao.getTeamHomeWorks(team_id);
	}
	@Override
	public TeamHomework getTeamHomework(String homework_id,String team_id) {
		return teamHomeworkDao.getHomeworkByID(homework_id,team_id);
	}
	
	public void addTeamHomework(String team_id,String homework_id){
		TeamHomework homework=new TeamHomework();
		homework.setHomework_id(homework_id);
		homework.setTeam_id(team_id);
		homework.setStatus(0);
		homework.setSubmit_time("");
		homework.setSubmitter("");
		homework.setCorrectInfo("");
		homework.setStudent_comment("");
		teamHomeworkDao.addHomework(homework);
	}
	public void setComment(String comment,String homework_id,String team_id){
		TeamHomework teamHomework=teamHomeworkDao.getHomeworkByID(homework_id, team_id);
		teamHomework.setStudent_comment(comment);
		teamHomeworkDao.updateHomework(teamHomework);
	}
	@Override
	public void updateTeamHomework(TeamHomework teamHomework) {
		teamHomeworkDao.updateHomework(teamHomework);		
	}
	@Override
	public List<HomeworkFile> getTeamHomewokFiles(String homework_id, String team_id) {
		// TODO Auto-generated method stub
		return teamHomeworkDao.getTeamHomeworkFiles(homework_id, team_id);
	}

}
