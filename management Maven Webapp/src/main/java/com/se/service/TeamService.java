package com.se.service;

import com.se.pojo.TeamConfig;

public interface TeamService {
	void addTeamConfig(String course_id,String year,String class_id,String max,String min);
	TeamConfig getTeamConfig(String course_id);
}
