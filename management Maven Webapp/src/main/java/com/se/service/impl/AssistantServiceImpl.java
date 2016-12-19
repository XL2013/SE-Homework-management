package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.AssistantDao;
import com.se.dao.CourseDao;
import com.se.pojo.Assistant;
import com.se.pojo.Course;
import com.se.service.AssistantService;

@Service("assistantService")
public class AssistantServiceImpl implements AssistantService{

	@Resource
	private AssistantDao assistantDao;
	@Resource
	private CourseDao courseDao;
	
	@Override
	public List<Assistant> getTeacherAssistants(String teacher_id) {
		
		return assistantDao.getAssistantByTID(teacher_id);
	}

	@Override
	public Assistant getTeamAssistant(String team_id) {
		// TODO Auto-generated method stub
		String id=assistantDao.getTeamAssistant(team_id);
		if(id==null) return null;
		return assistantDao.getAssistant(id);
	}

	@Override
	public void setTeamAssistant(String team_id, String assistant_id) {
		if(assistantDao.getTeamAssistant(team_id)==null)
			assistantDao.addTeamAssistant(team_id, assistant_id);	
		else
			assistantDao.setTeamAssistant(team_id, assistant_id);
	}

	@Override
	public List<String> getAssitantTeams(String assistant_id) {
		return assistantDao.getAssistantTeams(assistant_id);
	}

	@Override
	public List<Course> getAssistantCourses(String assistant_id) {
		String teacher_id=assistantDao.getAssistant(assistant_id).getTeacher_id();
		return courseDao.getAllCourse(teacher_id);
	}
	

}
