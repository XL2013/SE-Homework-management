package com.se.service;

import java.util.List;

import com.se.pojo.Assistant;

public interface AssistantService {
	List<Assistant> getTeacherAssistants(String teacher_id);
	Assistant getTeamAssistant(String team_id);
	void setTeamAssistant(String team_id,String assistant_id);
}
