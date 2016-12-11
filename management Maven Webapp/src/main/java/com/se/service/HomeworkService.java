package com.se.service;

import java.util.List;

import com.se.pojo.Homework;

public interface HomeworkService {
		List<Homework> getTeamHomeworks(String team_id);
}
