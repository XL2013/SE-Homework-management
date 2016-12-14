package com.se.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.se.dao.AssistantDao;
import com.se.pojo.Assistant;
import com.se.service.AssistantService;

@Service("assistantService")
public class AssistantServiceImpl implements AssistantService{

	@Resource
	private AssistantDao assistantDao;
	
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
	

}
