package com.se.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.mockito.internal.matchers.And;
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


	@Override
	public Map<String, Object> getTeamHomeworkViewData() {
		// TODO Auto-generated method stub
		List<TeamHomework> teamHomeworks = teamHomeworkDao.getTeamHomeworkViewData();
		List<String> homework_names = new ArrayList<>();
		List<Double> homework_ratios = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("teamHomeworks", teamHomeworks);
		for(TeamHomework teamHomework:teamHomeworks){
			String homework_id = teamHomework.getHomework_id();
			String homework_name = teamHomeworkDao.getHomeworkName(homework_id);
			double homework_ratio = homeworkDao.getRatioByHomeworkID(homework_id);
			homework_names.add(homework_name);
			homework_ratios.add(homework_ratio);
		}
		map.put("homework_ratios", homework_ratios);
		map.put("homework_names", homework_names);
		return map;
	}


	@Override
	public Map<String, Object> getTeamHomeworkViewData(String homework_name, String submit_time, String team_id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		
		if(submit_time!="" && team_id!=""){
			map.put("teamHomeworks",teamHomeworkDao.getTeamHomeworkViewDataByNameTeamIDTime(homework_name, team_id, submit_time));
		}
		else if(team_id!="" && team_id=="")
			map.put("teamHomeworks",teamHomeworkDao.getTeamHomeworkViewDataByNameTeamID(homework_name, team_id));
		else if(team_id=="" && team_id!=""){
			map.put("teamHomeworks",teamHomeworkDao.getTeamHomeworkViewDataByNameTime(homework_name, submit_time));
		}
		else{
			map.put("teamHomeworks", teamHomeworkDao.getTeamHomeworkViewDataByName(homework_name));
		}
		List<String> homework_names = new ArrayList<>();
		List<Double> homework_ratios = new ArrayList<>();
		for(TeamHomework teamHomework: (List<TeamHomework>)map.get("teamHomeworks")){
			String homework_id = teamHomework.getHomework_id();
			String homeworkname = teamHomeworkDao.getHomeworkName(homework_id);
			double homework_ratio = homeworkDao.getRatioByHomeworkID(homework_id);
			homework_names.add(homeworkname);
			homework_ratios.add(homework_ratio);
		}
		map.put("homework_names", homework_names);
		map.put("homework_ratios", homework_ratios);
		return map;
	}

}
