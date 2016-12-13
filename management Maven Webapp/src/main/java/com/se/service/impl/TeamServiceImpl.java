package com.se.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.se.dao.StudentDao;
import com.se.dao.TeamConfigDao;
import com.se.dao.TeamDao;
import com.se.pojo.Student;
import com.se.pojo.Team;
import com.se.pojo.TeamConfig;
import com.se.service.TeamService;

@Service("teamService")
public class TeamServiceImpl implements TeamService {
	
	@Resource
	private TeamConfigDao teamConfigDao;
	
	@Resource
	private TeamDao teamDao;
	
	@Resource
	private StudentDao studentDao;
	
	
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
		if(course_id==null) return null;
		return teamConfigDao.getTeamConfig(course_id);
	}


	@Override
	public Team getStudentTeam(String course_id, String student_id) {
		// TODO Auto-generated method stub
	   String  team_id=teamDao.searchTeamBySC(student_id, course_id);
	   if(team_id==null) return null;
	   return teamDao.getTeamInfo(team_id);
	}


	@Override
	public List<Student> getTeamStudents(String team_id) {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		if(team_id!=null){
			for(String student_id : teamDao.getStudentsByTID(team_id)){
				students.add(studentDao.getStudent(student_id));
			}
		}
		return students;
	}


	@Override
	public Team addTeam(String course_id, String student_id) {
		Team team=new Team();
		team.setCourse_id(course_id);
		team.setLeader_id(student_id);
		String prefix=teamConfigDao.getTeamConfig(course_id).getPrefix();
		int teamCount=teamDao.getCourseTeams(course_id).size();
		if(teamCount<10)
			prefix=prefix+"0"+String.valueOf(teamCount);
		else
			prefix=prefix+String.valueOf(teamCount);
		team.setTeam_id(prefix);
		team.setEmail("");
		teamDao.addTeam(team);
		teamDao.addTeamMember(team.getTeam_id(), student_id);
		return team;
	}


	@Override
	public Team getTeamById(String team_id) {
		
		return teamDao.getTeamInfo(team_id);
	}


	@Override
	public void addTeamMember(String team_id, String student_id) {
		
		teamDao.addTeamMember(team_id, student_id);
	}
	
	public void setTeamEmail(String team_id,String email){
		teamDao.setEmail(team_id, email);
	}


	@Override
	public List<Team> getCourseTeam(String course_id) {
		return teamDao.getCourseTeams(course_id);
	}
	
	public void setTeamLeader(String team_id,String leader_id){
		teamDao.setTeamLeader(team_id, leader_id);
	}
	

}
