package com.se.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.TeamConfigDao;
import com.se.pojo.TeamConfig;
import com.se.service.TeamService;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
	
	@Resource
	private TeamConfigDao teamConfigDao;
	
	public void addTeamConfig(String course_id,String year,String class_id,String max,String min){
			TeamConfig teamConfig=new TeamConfig();
			teamConfig.setCourse_id(course_id);
			teamConfig.setConfig_id(getConfigID(course_id));
			teamConfig.setPrefix(getPrefix(year, class_id));
			teamConfig.setTeam_max(Integer.parseInt(max));
			teamConfig.setTeam_min(Integer.parseInt(min));
			teamConfigDao.addTeamConfig(teamConfig);
	}
	
	
	public void readStudentInfo(String path,MultipartFile[] files){
		
		
	}
	/**
	 * 生成小组前缀的方式
	 * @param year
	 * @param class_id
	 * @return 小组前缀
	 */
	public String getPrefix(String year,String class_id){
			return year+class_id;
	}
	/**
	 * 生成小组设置id的方式
	 * @param course_id
	 * @return
	 */
	public String getConfigID(String course_id){
		return course_id+"1";
	}

	@Override
	public TeamConfig getTeamConfig(String course_id) {
		return teamConfigDao.getTeamConfig(course_id);
	}

}
