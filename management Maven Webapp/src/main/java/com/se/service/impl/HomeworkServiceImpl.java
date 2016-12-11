package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.TeamHomeworkDao;
import com.se.pojo.Homework;
import com.se.service.HomeworkService;

@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService{

	@Resource
	private TeamHomeworkDao teamHomeworkDao;
	@Override
	public List<Homework> getTeamHomeworks(String team_id) {
		
		return  teamHomeworkDao.getTeamHomeWorks(team_id);
	}

}
