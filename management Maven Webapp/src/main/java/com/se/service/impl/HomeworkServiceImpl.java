package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.TeamHomeworkDao;
import com.se.pojo.Homework;
import com.se.pojo.HomeworkFile;
import com.se.pojo.TeamHomework;
import com.se.dao.HomeworkDao;
import com.se.service.HomeworkService;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{
	
	@Resource
	private HomeworkDao homeworkDao;
	
	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Override
	public void addHomeworkInfos(List<Homework> homeworks) {
		// TODO Auto-generated method stub
		for(Homework homework :homeworks)
			homeworkDao.addHomework(homework);
	}


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
		teamHomeworkDao.setTeamHomeworkComment(homework_id, team_id, comment);
	}
	@Override
	public void updateTeamHomework(TeamHomework teamHomework) {
		teamHomeworkDao.updateTeamHomework(teamHomework);		
	}
	@Override
	public List<HomeworkFile> getTeamHomewokFiles(String homework_id, String team_id) {
		// TODO Auto-generated method stub
		return teamHomeworkDao.getTeamHomeworkFiles(homework_id, team_id);
	}

	@Override
	public void modifyHomeworkRatio(String homework_id, double ratio) {
		// TODO Auto-generated method stub
		homeworkDao.updateHomeworkRatio(homework_id, ratio);
	}
	
	public List<Homework> getHomeworksInfoByCourseID(String courseID){
		return homeworkDao.getHomeworksInfoByCourseID(courseID);
	}


	@Override
	public Homework getHomework(String homework_id) {
		// TODO Auto-generated method stub
		return homeworkDao.getHomeworkById(homework_id);
	}

}
